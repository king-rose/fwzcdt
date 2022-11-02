<template>
    <div class="app-container">
        <el-header style="width: 100%;margin-bottom: 10px">服务场景消费查询</el-header>
		<el-form v-show="showSearch" ref="queryForm" :inline="true" :model="queryParams" label-width="88px">
			<el-form-item label="消费方:" prop="reqSysId">
				<el-select v-model="queryParams.reqSysId">
					<el-option label="--请选择--" value="" />
					<el-option v-for="item in sysListOptions"
							   :key="item.sysId"
							   :value="item.sysId"
							   :label="item.sysName"
					/>
				</el-select>
			</el-form-item>
			<el-form-item label="提供方:" prop="proSysId">
				<el-select v-model="queryParams.proSysId">
					<el-option label="--请选择--" value="" />
					<el-option v-for="item in sysListOptions"
							   :key="item.sysId"
							   :value="item.sysId"
							   :label="item.sysName"
					/>
				</el-select>
			</el-form-item>
			<el-form-item label="服务:" prop="serviceId">
				<el-select v-model="queryParams.serviceId" placeholder="--请选择--">
					<el-option value="" label="--请选择--"></el-option>
					<el-option value="1" label="AdjustCreditLimit——信用卡余额"></el-option>
					<el-option value="2" label="AuthorPOS——POS预授权"></el-option>
				</el-select>
			</el-form-item>
			<el-form-item label="提供方接口:" prop="proTradeCode">
				<el-select v-model="queryParams.proTradeCode" placeholder="--请选择--">
					<el-option value="" label="--请选择--"></el-option>
				</el-select>
			</el-form-item>
			<el-form-item label="消费状态:" prop="state">
				<el-select v-model="queryParams.state" placeholder="--请选择--">
					<el-option value="-1" label="--请选择--"></el-option>
					<el-option value="1" label="预消费"></el-option>
					<el-option value="2" label="审核中"></el-option>
					<el-option value="3" label="已发布"></el-option>
					<el-option value="4" label="已废弃"></el-option>
				</el-select>
			</el-form-item>
			<el-form-item>
				<el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
				<el-button type="warning" icon="el-icon-fresh" size="mini" @click="resetQuery">重置</el-button>
			</el-form-item>
		</el-form>

        <el-row :gutter="10" class="mb8" style="margin-top: 10px" v-show="!queryParams.mappingId">
            <el-col :span="1.5">
                <el-button
                    type="primary"
                    icon="el-icon-plus"
                    size="mini"
                    @click="handleAdd"
                    v-hasPermi="['systemLIst:systemLIst:add']"
                >新增
                </el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="success"
                    icon="el-icon-edit"
                    size="mini"
                    :disabled="single"
                    @click="handleUpdate"
                    v-hasPermi="['systemLIst:systemLIst:edit']"
                >修改
                </el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="danger"
                    icon="el-icon-delete"
                    size="mini"
                    :disabled="multiple"
                    @click="handleDelete"
                    v-hasPermi="['systemLIst:systemLIst:remove']"
                >删除
                </el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="warning"
                    icon="el-icon-download"
                    size="mini"
                    @click="handleExport"
                    v-hasPermi="['systemLIst:systemLIst:export']"
                >导出
                </el-button>
            </el-col>
            <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="mappingList" @selection-change="handleSelectionChange"
                  :cell-style="cellStyle">
            <el-table-column type="selection" width="55" align="center"/>
            <el-table-column label="消费方" align="center" prop="reqSysName"/>
            <el-table-column label="服务编号" align="center" prop="svcCode" width="150"/>
            <el-table-column label="服务中文名" align="center" prop="svcName" style="color: #49ba9c"/>
            <el-table-column label="场景码" align="center" prop="svCode"/>
            <el-table-column label="场景中文名" align="center" prop="svName"/>
            <el-table-column label="关联接口" align="center" prop="svInterface" width="150"/>
            <el-table-column label="提供方" align="center" prop="proSysName"/>
            <el-table-column label="状态" align="center" prop="state">
                <template slot-scope="scope">
                    <span>{{scope.row.state=='1'? '预消费':scope.row.state=='2'? '审核中':scope.row.state=='3'? '已发布':'已废弃'}}</span>
                </template>
            </el-table-column>
            <el-table-column label="消费时间" align="center" prop="crtTime"/>
            <el-table-column label="超时时间" align="center" prop="timeOut"/>
            <el-table-column label="业务交易代码" align="center" prop="businessCode"/>
            <el-table-column label="业务交易名称" align="center" prop="businessName"/>
            <el-table-column label="集成方式" align="center" prop="svIntegrationMode">
                <template slot-scope="scope">
                    <span>{{scope.row.svIntegrationMode=='0'? '内联ESB':scope.row.state=='1'? '外联ESB':'直连'}}</span>
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

		<el-dialog :visible.sync="open" title="服务消费申请">
			<el-form :model="form" ref="form" label-width="128px">
				<el-row :gutter="10">
					<el-col :span="12">
						<el-form-item label="项目编号:">
							<el-select v-model="form.projectCode">
								<el-option label="--请选择--" value=""/>
							</el-select>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="消费方:">
							<el-select v-model="form.proSysId">
								<el-option label="--请选择--" value=""/>
							</el-select>
						</el-form-item>
					</el-col>
				</el-row>

				<el-row :gutter="10">
					<el-col :span="12">
						<el-form-item label="提供方:">
							<el-select v-model="form.projectCode">
								<el-option label="--请选择--" value=""/>
							</el-select>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="服务:">
							<el-select v-model="form.proSysId">
								<el-option label="--请选择--" value=""/>
							</el-select>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row :gutter="10">
					<el-col :span="12">
						<el-form-item label="提供方接口:">
							<el-select v-model="form.projectCode">
								<el-option label="--请选择--" value=""/>
							</el-select>
						</el-form-item>
					</el-col>
				</el-row>
			</el-form>

			<el-row style="width: 40%;text-align: center" :gutter="10" class="m20 mb20">
				<el-button style="background-color: #1ab394" type="info" size="mini">提交</el-button>
				<el-button style="background-color: #1ab394" type="info" size="mini">添加到预消费清单</el-button>
				<el-button style="background-color: #1ab394" type="info" size="mini">查看预消费清单</el-button>
			</el-row>

			<el-table :data="serviceSceneList" v-loading="loading" :height="250">
				<el-table-column type="selection" width="50px"></el-table-column>
				<el-table-column label="服务编号" min-width="8%"></el-table-column>
				<el-table-column label="服务名称" min-width="8%"></el-table-column>
				<el-table-column label="场景码" min-width="7%"></el-table-column>
				<el-table-column label="场景名称" min-width="10%"></el-table-column>
				<el-table-column label="关联接口" min-width="10%"></el-table-column>
				<el-table-column label="提供方" min-width="10%"></el-table-column>
				<el-table-column label="状态" min-width="7%"></el-table-column>
				<el-table-column label="超时时间" min-width="10%"></el-table-column>
				<el-table-column label="业务交易代码" min-width="12.5%"></el-table-column>
				<el-table-column label="业务交易名称" min-width="12.5%"></el-table-column>
			</el-table>
		</el-dialog>
    </div>
</template>

<script>
    import {listMapping,delMappingLIst} from "@/api/service/mappingrelation";
	import {listSystem} from "@/api/system/list";

    export default {
        name: "consumeRelation",
        components: {},
        data() {
            return {
                // 遮罩层
                loading: true,
                // 选中数组
                ids: [],

				serviceSceneList:[],
                // 非单个禁用
                single: true,
                // 非多个禁用
                multiple: true,
                // 显示搜索条件
                showSearch: true,
                // 总条数
                total: 0,
                // 系统列表表格数据
                mappingList: [],
				sysListOptions:[],
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
                    reqSysId: null,
                    proSysId: null,
                    serviceId: null,
                    proTradeCode: null,
                    state: null,
                    mappingId:''
                },
                // 表单参数
                form: {},
                // 表单校验
                rules: {}
            };
        },
		activated() {
			if (this.$route.query.reqSysId){
				this.queryParams.reqSysId = this.$route.query.reqSysId;
			}
			if (this.$route.query.proSysId){
				this.queryParams.proSysId = this.$route.query.proSysId;
			}
			if (this.$route.query.proSysId || this.$route.query.reqSysId){
				this.showSearch = false
			}
			this.getList();
		},
		created() {
			this.getSysData();
        },
        methods: {
            cellStyle(row) {//根据报警级别显示颜色
                if (row.column.label === "场景中文名" || row.column.label === "状态") {
                    return 'color:#49ba9c'
                }
            },
			getSysData(){
				listSystem().then(res=>{
					this.sysListOptions = res.rows
				})
			},
            /** 查询消费列表 */
            getList() {
				this.loading = true;
                listMapping(this.queryParams).then(res=>{
                    this.mappingList = res.rows;
                    this.total = res.total;
                    this.loading = false;
                })
            },
            // 取消按钮
            cancel() {
                this.open = false;
                this.reset();
            },
            // 表单重置
            reset() {
                this.form = {
                    xff: null,
                    tgf: null,
                    fw: null,
                    tgfjk: null,
                    xfzt: null
                };
                this.resetForm("form");
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
                this.ids = selection.map(item => item.mappingId)
                this.single = selection.length !== 1
                this.multiple = !selection.length
            },
            /** 新增按钮操作 */
            handleAdd() {
                this.reset();
                this.open = true;
                this.title = "添加系统列表";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const sysId = row.sysId || this.ids
                getSystemLIst(sysId).then(response => {
                    this.form = response.data;
                    this.open = true;
                    this.title = "修改系统列表";
                });
            },
            /** 提交按钮 */
            submitForm() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.sysId != null) {
                            updateSystemLIst(this.form).then(response => {
                                this.msgSuccess("修改成功");
                                this.open = false;
                                this.getList();
                            });
                        } else {
                            addSystemLIst(this.form).then(response => {
                                this.msgSuccess("新增成功");
                                this.open = false;
                                this.getList();
                            });
                        }
                    }
                });
            },
            /** 删除按钮操作 */
            handleDelete(row) {
				console.log(this.ids)
                const mappingId = row.mappingId || this.ids;
                this.$confirm('是否确认删除系统列表编号为"' + mappingId + '"的数据项?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function () {
                    return delMappingLIst(mappingId);
                }).then(() => {
                    this.getList();
                    this.msgSuccess("删除成功");
                })
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$confirm('是否确认导出所有系统列表数据项?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function () {
                    return exportSystemLIst(queryParams);
                }).then(response => {
                    this.download(response.msg);
                })
            }
        }
    };
</script>
<style lang="scss">
    .el-container {
        border: solid 1px #d7e6b7;
    }

    .el-header {
        color: #333;
        text-align: center;
        line-height: 60px;
        border: solid 1px #f2f2f2;
        width: 90%;
        margin: 0px auto;
        margin-top: 10px;
        background-color: #f2f2f2;
    }

    .el-main {
        color: #333;
        text-align: center;
        line-height: 80px;
    }
</style>
