<template>
    <div class="app-container">
        <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="168px" size="mini">
            <el-form-item label="需求编号:" prop="demandId">
                <el-input v-model="queryParams.demandId" placeholder="需求编号.."/>
            </el-form-item>
            <el-form-item label="需求名称:" prop="demandName">
                <el-input v-model="queryParams.demandName" placeholder="请输入需求名称.."/>
            </el-form-item>
            <el-form-item label="申请人名称:" prop="applyUserName">
                <el-input v-model="queryParams.applyUserName" placeholder="申请人名称.."/>
            </el-form-item>
            <el-form-item label="需求状态:" prop="demandState">
                <el-select v-model="queryParams.demandState">
                    <el-option
                        v-for="dict in demandStateOptions"
                        :key="dict.dictValue"
                        :label="dict.dictLabel"
                        :value="dict.dictValue"
                    />
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
                <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
            </el-form-item>
        </el-form>

        <div class="all-table">
            <el-row :gutter="10" class="mb20 mt20">
                <el-col :span="1.5">
                    <el-button
                        type="primary"
                        icon="el-icon-folder-add"
                        size="mini"
                        @click="handleInput"
                    >需求录入
                    </el-button>
                </el-col>
                <el-col :span="1.5">
                    <el-button
                        type="warning"
                        icon="el-icon-upload2"
                        size="mini"
                        @click="handleExport"
                    >导出
                    </el-button>
                </el-col>
                <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
            </el-row>
            <el-table class="mt20" v-loading="loading" :data="demandList" @selection-change="handleSelectionChange">
                <el-table-column type="selection" width="50" align="center"/>
                <el-table-column label="序号" width="50" align="center">
                    <template slot-scope="scope">
                        {{ scope.$index + 1 }}
                    </template>
                </el-table-column>
                <el-table-column label="需求编号" align="center" prop="demandId" min-width="15.7%" />
                <el-table-column label="需求名称" align="center" prop="demandName" min-width="15.7%"/>
                <el-table-column label="需求描述" align="center" prop="demandDes" min-width="31.6%"/>
                <el-table-column label="需求状态" align="center" prop="demandState" min-width="10.5%" :formatter="statusFormat">
                    <template slot-scope="scope">
						<el-link @click="handleBreak(scope.row)" :type="scope.row.demandState==='0'? 'warning':scope.row.demandState==='1'? 'primary':'success'">{{statusFormat(scope.row,scope.column)}}</el-link>
                    </template>
                </el-table-column>
				<el-table-column align="center" label="任务状态" prop="taskState" min-width="10.5%">
					<template slot-scope="scope">
						<span
							:class="scope.row.taskState==='0'? 'demandClass_4':scope.row.taskState==='1'? 'demandClass':scope.row.taskState==='2'? 'demandClass_1':scope.row.taskState==='3'?'demandClass_2':'demandClass_3'">
							{{scope.row.taskState === '0'?'未受理': scope.row.taskState === '1' ? '已派送' : scope.row.taskState === '2' ? '已受理' : scope.row.taskState === '3' ? '已通过':'已驳回' }}</span>
					</template>
				</el-table-column>
                <el-table-column label="需求申请日期" align="center" prop="applyTime" min-width="10.5%"/>
                <el-table-column label="需求申请人" align="center" prop="applyUserName" min-width="10.5%"/>
                <el-table-column label="操作" align="center" min-width="15.7%" class-name="small-padding fixed-width">
                    <template slot-scope="scope">
                        <el-button
                            size="mini"
                            type="text"
                            @click="handleUpdate(scope.row)"
                        >
                            <svg-icon icon-class="change"></svg-icon>
                            变更
                        </el-button>

                        <el-button
                            size="mini"
                            type="text"
                            icon="el-icon-delete"
                            @click="handleDelete(scope.row)"
                        >删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
            <pagination
                v-show="total>0"
                :total="total"
                :page.sync="queryParams.pageNum"
                :limit.sync="queryParams.pageSize"
                @pagination="getList"
            />

            <!--需求变更-->
            <el-dialog :title="title" :visible.sync="open" append-to-body center>
                <el-form ref="form" :model="form" :rules="rules" label-width="120px">
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="需求编号" prop="demandId">
                                <el-input disabled v-model="form.demandId" placeholder="需求编号"/>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="需求名称" prop="demandName">
                                <el-input disabled v-model="form.demandName"  placeholder="需求名称" />
                            </el-form-item>
                        </el-col>
                        <el-col :span="24">
                            <el-form-item label="需求描述" prop="demandDes">
                                <el-input disabled type="textarea"  v-model="form.demandDes" placeholder="需求名称"/>
                            </el-form-item>
                        </el-col>
                        <el-col :span="24">
                            <el-form-item  label="变更内容" prop="changeContent">
                                <el-input required type="textarea" v-model="form.changeContent" placeholder="变更内容"/>
                            </el-form-item>
                        </el-col>
                        <el-col :span="24">
                            <el-form-item  label="变更原因" prop="changeReason">
                                <el-input required type="textarea" v-model="form.changeReason" placeholder="变更原因"/>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item  label="变更周期" prop="changeCycle">
                                <el-input required v-model="form.changeCycle" placeholder="变更周期"/>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item  label="延期时间" prop="delayTime">
                                <el-date-picker style="width: 100%" required
                                    v-model="form.delayTime"
                                    type="date"
                                    value-format="yyyy-MM-dd"
                                    placeholder="选择日期">
                                >
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button type="primary" @click="submit">确 定</el-button>
                    <el-button @click="cancel">取 消</el-button>
                </div>
            </el-dialog>
        </div>
    </div>
</template>

<script>

import {getDemandInfo, listDemand,updateDemand,delDemandInfo} from "@/api/demand/demand";

export default {
    name: "demand",
    data() {
        return {
            //显示搜索条件
            showSearch: true,
            // 非单个禁用
            single: true,
            // 非多个禁用
            multiple: true,
            //遮罩层
            loading: true,
            // 选中数组
            ids: [],

            //查询参数
            queryParams: {
                //分页参数
                pageNum: 1,
                pageSize: 10,
                demandId: undefined,
                demandName: undefined,
				applyUserName: undefined,
                demandState: undefined
            },
            //需求状态
            demandStateOptions:[],

            //表单参数
            form:{
            },

            //总条数
            total: 0,
            //列表数据
            demandList: [],

            //模态框参数
            open: false,
            title: '',

            //表单校验
            rules:{
                changeContent:[
                    {required: true, message: "变更内容不能为空", trigger: "blur"}
                ],
                changeReason:[
                    {required: true, message: "变更原因不能为空", trigger: "blur"}
                ],
                changeCycle:[
                    {required: true, message: "变更周期不能为空", trigger: "blur"}
                ],
                delayTime:[
                    {required: true, message: "延期时间不能为空", trigger: "blur"}
                ]
            }

        }
    },
    created() {
        this.getDicts("aesb_demand_state").then(response => {
            this.demandStateOptions = response.data;
        });
        this.getList();
    },
    methods: {
        //需求列表
        getList() {
            this.loading = true;
            listDemand(this.queryParams).then(response => {
                this.demandList = response.rows;
                this.total = response.total;
            })
            this.loading = false;
        },
        /** 搜索按钮操作 */
        handleQuery() {
            this.queryParams.pageNum = 1;
            this.getList();
        },
        /** 重置按钮操作 */
        resetQuery() {
            this.resetForm("queryForm");
            this.handleQuery();
        },
        // 多选框选中数据
        handleSelectionChange(selection) {
            this.ids = selection.map(item => item.userId);
            this.single = selection.length !== 1;
            this.multiple = !selection.length;
        },
        // 字典状态字典翻译
        statusFormat(row, column) {
            return this.selectDictLabel(this.demandStateOptions, row.demandState);
        },

        //取消
        cancel() {
            this.open = false;
            this.reset();
        },
        //表单重置
        reset(){
            this.form = {
                demandId: undefined,
                demandName: undefined,
                demandState: "0",
                changeContent:undefined,
                changeReason:undefined,
                changeCycle:undefined,
                delayTime:undefined

            };
            this.resetForm("form");
        },


        //需求录入按钮
        handleInput() {
            this.$router.push({path: `/need/add`})

        },
        //变更按钮
        handleUpdate(row) {
            this.reset();
            getDemandInfo(row.demandId).then(response=>{
                this.open = true;
                this.title = '需求变更';
                this.form = response.data
            })
        },
		//点击需求状态
		handleBreak(row){
			this.$router.push(
				{
					path: `/need/add`,
					query: {
						demandId: row.demandId,
						demandState:row.demandState,
						taskState:row.taskState
					}
				}
			)
		},
        //删除按钮
        handleDelete(row) {
			const demandId = row.demandId;
			this.$confirm('是否确认删除名称为"' + row.demandName + '"的数据项?', "警告", {
				confirmButtonText: "确定",
				cancelButtonText: "取消",
				type: "warning"
			}).then(function () {
				return delDemandInfo(demandId);
			}).then(() => {
				this.getList();
				this.msgSuccess("删除成功");
			})
        },

        //导出
        handleExport() {
        },
        //提交
        submit(){
            this.$refs["form"].validate(valid => {
                if (valid){
                    updateDemand(this.form).then(response => {
                        this.msgSuccess("修改成功");
                        this.open = false;
                        this.getList();
                    });
                }
            })
        }
    }
}
</script>

<style lang="scss" scoped>
    .el-divider--horizontal{
        height: 5px;
        margin-top: 10px;
        margin-bottom: 15px;
    }
	.demandClass{
		color: #686464;
	}
	.demandClass:hover{
		cursor: pointer;
		text-decoration: underline;
	}
	.demandClass_1 {
		color: #1c84c6;
	}

	.demandClass_1:hover {
		cursor: pointer;
		text-decoration: underline;
	}

	.demandClass_2 {
		color: #1ab394;
	}

	.demandClass_2:hover {
		cursor: pointer;
		text-decoration: underline;
	}
	.demandClass_3{
		color: #ff4949;
	}
	.demandClass_4{
		color: #ffba00;
	}

</style>
