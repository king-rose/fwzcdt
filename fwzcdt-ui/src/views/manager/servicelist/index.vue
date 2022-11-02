<template>
    <div class="app-container">

        <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" class="formClass">

            <el-form-item label="服务查询" label-width="200px" class="search">

                <el-input v-model="queryParams.svcName" class="search"
                          style="width: 1000px;font-size: 20px;margin-left: 15px" size="default" :clearable="true"
                          @clear="getList">
                    <el-button @click="handleQuery" slot="append" icon="el-icon-search"></el-button>
                </el-input>
            </el-form-item>


        </el-form>

        <el-row :gutter="10" class="mb8">
            <el-col :span="1.5">
                <el-button
                    type="primary"
                    plain
                    icon="el-icon-plus"
                    size="mini"
                    @click="handleAdd"
                >新增
                </el-button>
            </el-col>

            <el-col :span="1.5">
                <el-button
                    type="success"
                    plain
                    icon="el-icon-plus"
                    size="mini"
                    @click="handleImport"
                >导入
                </el-button>
            </el-col>

            <el-col :span="1.5">
                <el-button
                    type="warning"
                    plain
                    icon="el-icon-search"
                    :disabled="radio==null"
                    size="mini"
                    @click="handleDetail"
                >查看详情
                </el-button>
            </el-col>

            <el-col :span="1.5">
                <el-button
                    type="info"
                    plain
                    icon="el-icon-edit"
                    :disabled="radio==null"
                    size="mini"
                    @click="handleEdit"
                >修改
                </el-button>
            </el-col>

            <el-col :span="1.5">
                <el-button
                    type="danger"
                    plain
                    icon="el-icon-delete"
                    :disabled="radio==null"
                    size="mini"
                    @click="handleDelete"
                >删除
                </el-button>
            </el-col>

            <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table
            ref="fileTable"
            v-loading="loading"
            highlight-current-row
            @row-click="handleClickTableRow"
            @current-change="handleSelectionChange"
            :cell-style="cellStyle"
            :header-cell-style="{'background-color': '#eef1f6','color': 'rgb(96,98,102)','font-size':'18','border-bottom': '1px rgb(103, 194, 58) solid'}"
            :data="senceList">
            <el-table-column min-width="5%" align="center" header-align="center">
                <template slot-scope="scope">
                    <el-radio v-model="radio" :label="scope.row.svcId">
                        <span class="el-radio__label"></span>
                    </el-radio>
                </template>
            </el-table-column>
            <el-table-column prop="serviceHeadClass" label="服务大类"></el-table-column>
            <el-table-column prop="serviceSmallClass" label="服务子类"></el-table-column>
            <el-table-column prop="svcCode" label="服务英文名"></el-table-column>
            <el-table-column prop="svcName" label="服务名称">
                <template slot-scope="scope">
					<span :title="scope.row.svcName" class="cell-hover" @click="toDetail(scope.row.svcId)">{{scope.row.svcName}}</span>
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
        <!-- 添加或修改部门对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="100px">
                <el-form-item label="服务大类" prop="serviceHeadClass">
                    <el-select style="width: 100%" v-model="form.serviceHeadClass" filterable placeholder="--请选择--"
                        :disabled="source"
                    >
                        <el-option
                            v-for="item in serviceHeadClassOptions"
                            :key="item"
                            :label="item"
                            :value="item">
                        </el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="服务子类" prop="serviceSmallClass">
                    <el-select style="width: 100%" v-model="form.serviceSmallClass" filterable placeholder="--请选择--"
                        :disabled="source"
                    >
						<el-option
							v-for="item in serviceSmallClassOptions"
							:key="item"
							:label="item"
							:value="item">
						</el-option>
                    </el-select>
                </el-form-item>

				<el-form-item label="英文名称" prop="svcCode">
					<el-input v-model="form.svcCode" placeholder="请输入服务英文名称"/>
				</el-form-item>

                <el-form-item label="中文名称" prop="svcName">
                    <el-input v-model="form.svcName" placeholder="请输入服务中文名称"/>
                </el-form-item>

                <el-form-item label="服务描述" prop="svcDes">
                    <el-input
                        type="textarea"
                        :autosize="{ minRows: 2, maxRows: 4}"
                        placeholder="请输入服务描述"
                        v-model="form.svcDes">
                    </el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="submitForm">确 定</el-button>
                <el-button @click="cancel">取 消</el-button>
            </div>
        </el-dialog>

        <!-- 导入对话框 -->
        <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px" append-to-body>

            <el-upload
                ref="upload"
                :limit="1"
                accept=".xlsx, .xls"
                :headers="upload.headers"
                :action="upload.url + '?updateSupport=' + upload.updateSupport"
                :disabled="upload.isUploading"
                :on-progress="handleFileUploadProgress"
                :on-success="handleFileSuccess"
                :auto-upload="false"
                drag
            >
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                <div class="el-upload__tip text-center" slot="tip">
                    <div class="el-upload__tip" slot="tip">
                        <el-checkbox v-model="upload.updateSupport"/>
                        是否更新已经存在的服务数据
                    </div>
                    <span>仅允许导入xls、xlsx格式文件。</span>
                    <el-link type="primary" :underline="false" style="font-size:12px;vertical-align: baseline;"
                             @click="importTemplate">下载模板
                    </el-link>
                </div>
            </el-upload>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="submitFileForm">确 定</el-button>
                <el-button @click="upload.open = false">取 消</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import {serviceList, importTemplate,delServiceListInfo,addService,updateService,getServiceInfo,headClassList,smallClassList} from "@/api/service/oprservice";
    import {getToken} from "@/utils/auth";

    export default {
        name: "ServiceList",
        dicts: ['sys_normal_disable'],
        components: {},
        data() {
            return {
                radio: null, // 如果使用单选框，定义一个model值
                currentSelectItem: {}, //当前选中的值
                source:true,
                // 遮罩层
                loading: true,
                // 显示搜索条件
                showSearch: true,
                // 表格树数据
                senceList: [],
                total: 0,
                multipleSelection: [], // 表格被选中的内容
                serviceHeadClassOptions: [],
                serviceSmallClassOptions: [],
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,

                // 查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
                    svcName: undefined
                },
                // 用户导入参数
                upload: {
                    // 是否显示弹出层（用户导入）
                    open: false,
                    // 弹出层标题（用户导入）
                    title: "",
                    // 是否禁用上传
                    isUploading: false,
                    // 是否更新已经存在的用户数据
                    updateSupport: 0,
                    // 设置上传的请求头部
                    headers: {Authorization: "Bearer " + getToken()},
                    // 上传的地址
                    url: process.env.VUE_APP_BASE_API + "/oprService/upload",
                    // 上传的多sheet表名
                },
                // 表单参数
                form: {},

                query: {},
                fileData: [],

                groupQuery: null,

                // 表单校验
                rules: {
                    parentId: [
                        {required: true, message: "上级部门不能为空", trigger: "blur"}
                    ],
                    deptName: [
                        {required: true, message: "部门名称不能为空", trigger: "blur"}
                    ],
                    orderNum: [
                        {required: true, message: "显示排序不能为空", trigger: "blur"}
                    ],
                    email: [
                        {
                            type: "email",
                            message: "'请输入正确的邮箱地址",
                            trigger: ["blur", "change"]
                        }
                    ],
                    phone: [
                        {
                            pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
                            message: "请输入正确的手机号码",
                            trigger: "blur"
                        }
                    ]
                }
            };
        },
        created() {
			//服务大类
			this.getHeadClassList();
			//服务子类
			this.getSmallClassList();
            this.getList();
        },
        methods: {
			toDetail(svcId){
				this.$router.push({path:'/service/fwxx',query:{svcId:svcId}})
			},
            handleClickTableRow(row, column, event) {
                this.radio = row.svcId
            },
            handleSelectionChange(row) {
                this.currentSelectItem = row;
            },
            cellStyle(row, column, rowIndex, columnIndex) {//根据报警级别显示颜色
                // console.log(row);
                // console.log(row.column);
                if (row.column.label === "服务名称") {
                    return 'color:#49ba9c'
                }
            },
			//获取服务大类列表
			getHeadClassList(){
				headClassList().then(response=>{
					this.serviceHeadClassOptions = response.data
				})
			},
			//获取服务子类列表
			getSmallClassList(){
				smallClassList().then(response=>{
					this.serviceSmallClassOptions = response.data
				})
			},

            /** 查询服务列表 */
            getList() {
                let that = this
                that.radio = null;
                that.loading = true;
                serviceList(that.queryParams).then(resp => {
                    that.senceList = resp.rows
                    that.total = resp.total
                    that.loading = false;
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
                    deptId: undefined,
                    parentId: undefined,
                    deptName: undefined,
                    orderNum: undefined,
                    leader: undefined,
                    phone: undefined,
                    email: undefined,
                    status: "0"
                };
                this.resetForm("form");
            },
            /** 搜索按钮操作 */
            handleQuery() {
                this.getList();
            },
            /** 重置按钮操作 */
            resetQuery() {
                this.resetForm("queryForm");
                this.handleQuery();
            },
            /** 新增按钮操作 */
            handleAdd() {
                this.reset();
                this.source = false;
                this.open = true;
                this.title = "添加服务信息";
            },

            /** 下载模板操作 */
            importTemplate() {
                importTemplate().then(response => {
                    this.download(response.msg);
                });
            },
            // 文件上传中处理
            handleFileUploadProgress(event, file, fileList) {
                this.upload.isUploading = true;
            },
            // 文件上传成功处理
            handleFileSuccess(response, file, fileList) {
                this.upload.open = false;
                this.upload.isUploading = false;
                this.$refs.upload.clearFiles();
                this.$alert("<div style='overflow: auto;overflow-x: hidden;max-height: 70vh;padding: 10px 20px 0;'>" + response.msg + "</div>", "导入结果", {dangerouslyUseHTMLString: true});
                this.getList();
            },

            // 提交上传文件
            submitFileForm() {
                this.$refs.upload.submit();
            },

            /** 提交按钮 */
            submitForm: function () {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.svcId != undefined) {
                            updateService(this.form).then(response => {
                                this.msgSuccess("修改成功");
                                this.open = false;
                                this.getList();
                            });
                        } else {
							addService(this.form).then(response => {
                                this.msgSuccess("新增成功");
                                this.open = false;
                                this.getList();
                            });
                        }
                    }
                });
            },
            /** 删除按钮操作 */
            handleDelete() {
                console.log(this.currentSelectItem)
                const svcId = this.currentSelectItem.svcId;
                this.$confirm('是否确认删除名称为"' + this.currentSelectItem.svcName + '"的数据项?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function () {
                    return delServiceListInfo(svcId);
                }).then(() => {
                    this.getList();
                    this.msgSuccess("删除成功");
                })
            },
            //导入
            handleImport() {
                this.upload.title = "服务批量导入";
                this.upload.open = true;
            },
            //查询详情
            handleDetail(){
                this.$router.push({path:`/service/fwxx`,query:{svcId:this.currentSelectItem.svcId}})
            },
            //修改
            handleEdit(){
                this.source = true;
                this.open = true;
                this.title = "修改服务信息";
                getServiceInfo(this.currentSelectItem.svcId).then(res=>{
                    console.log(res)
                    this.form = res.data
                })
            }
        }
    };
</script>
<style lang="scss">

    .search .el-form-item__label {
        font-size: 20px;
        font-weight: normal;
    }
</style>
