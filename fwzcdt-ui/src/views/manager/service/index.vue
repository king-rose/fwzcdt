<template>
    <div class="app-container">

        <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch">
            <el-form-item label="服务场景查询" prop="groupQuery">
                <el-switch
                    style="margin-left: 35px"
                    v-model="groupQuery"
                    active-color="#409EFF"
                    inactive-color="#13ce66"
                    active-text="组合查询"
                    inactive-text="模糊查询">
                </el-switch>

            </el-form-item>
            <el-form-item>
                <el-input
                    v-model="queryParams.svName"
                    placeholder="请输入服务名称"
                    clearable
                    size="small"
                    @keyup.enter.native="handleQuery"
                />
            </el-form-item>

            <el-form-item>
                <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
                <!--<el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>-->
            </el-form-item>

            <!--组合查询-->
            <div v-if="groupQuery">
                <el-row>
                    <el-col :span="24">
                        <el-form-item label="所属系统:">
                            <el-select v-model="queryParams.scnSysId" filterable>
                                <el-option label="全部" value=""></el-option>
                                <el-option
                                    v-for="item in sysOptions"
                                    :key="item.sysId"
                                    :label="item.sysName"
                                    :value="item.sysId">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="可用状态:">
                            <el-select v-model="queryParams.svStatus">
                                <el-option label="全部" value=""></el-option>
                                <el-option label="可用" value="1"></el-option>
                                <el-option label="不可用" value="0"></el-option>
                            </el-select>
                        </el-form-item>

                        <el-form-item label="是否对标:">
                            <el-select v-model="queryParams.stdFieldCheck">
                                <el-option label="全部" value=""></el-option>
                                <el-option label="是" value="1"></el-option>
                                <el-option label="否" value="0"></el-option>
                            </el-select>
                        </el-form-item>

                        <el-form-item label="外部接口码:">
                            <el-select v-model="queryParams.svExternalInterfaceCode" placeholder="--请选择--">
                                <el-option label="--请选择--" value="-1"></el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="是否适配:">
                            <el-select v-model="queryParams.jkAdapFlag">
                                <el-option label="全部" value=""></el-option>
                                <el-option label="是" value="1"></el-option>
                                <el-option label="否" value="0"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>

                    <el-col :span="24">
                        <el-form-item label="场景类型:">
                            <el-select v-model="queryParams.svType" placeholder="--请选择--">
                                <el-option label="标准场景" value="1"></el-option>
                                <el-option label="主题场景" value="0"></el-option>
                                <el-option label="组合场景" value="2"></el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="是否开放:">
                            <el-select v-model="queryParams.isOpen" placeholder="--请选择--">
                                <el-option label="开放" value="1"></el-option>
                                <el-option label="不开放" value="0"></el-option>
                            </el-select>
                        </el-form-item>


                        <el-form-item label="是否被消费:">
                            <el-select v-model="queryParams.isConsumption" placeholder="--请选择--">
                                <el-option label="未被消费" value="1"></el-option>
                                <el-option label="已被消费" value="0"></el-option>
                            </el-select>
                        </el-form-item>

                        <el-form-item label="是否校验:">
                            <el-select v-model="queryParams.xmlValidFlag" placeholder="--请选择--">
                                <el-option label="是" value="1"></el-option>
                                <el-option label="否" value="0"></el-option>
                            </el-select>
                        </el-form-item>
						<el-form-item label="审核状态:">
							<el-select v-model="queryParams.auditState" placeholder="--请选择--">
								<el-option label="审核失败" value="3"></el-option>
								<el-option label="审核通过" value="2"></el-option>
								<el-option label="审核中" value="1"></el-option>
								<el-option label="待提交" value="0"></el-option>
								<el-option label="全部" value=""></el-option>
							</el-select>
						</el-form-item>
                        <el-form-item label="查询日期:">
                            <el-date-picker
                                v-model="datetimes"
                                @change="changeTime"
                                value-format="yyyy-MM-dd"
                                type="daterange"
                                range-separator="至"
                                start-placeholder="开始日期"
                                end-placeholder="结束日期">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-row>

            </div>
        </el-form>

        <el-row :gutter="10" class="mb8">
            <el-col :span="1.5">
                <el-button size="mini" type="success" icon="el-icon-info" plain @click="release()">发布</el-button>
            </el-col>

            <el-col :span="1.5">
                <el-button icon="el-icon-search" size="mini" type="warning" :disabled="single" plain
                           @click="handleDetail">查看详情
                </el-button>
            </el-col>

            <el-col :span="1.5">
                <el-button size="mini" type="danger" icon="el-icon-delete" :disabled="multiple" plain
                           @click="handleDelete">删除
                </el-button>
            </el-col>
        </el-row>

        <el-table
			class="mt20"
            ref="fileTable"
            v-loading="loading"
            @selection-change="handleSelectionChange"
            highlight-current-row
            @row-click="handleClickTableRow"
            :cell-style="cellStyle"
            :header-cell-style="{'background-color': '#eef1f6','color': 'rgb(96,98,102)','font-size':'18','border-bottom': '1px rgb(103, 194, 58) solid'}"
            :data="senceList">
            <el-table-column type="selection" min-width="3%" align="center"></el-table-column>
            <el-table-column prop="svcCode" label="服务编号" min-width="10%" align="center"></el-table-column>
            <el-table-column prop="svcName" label="服务名称" min-width="10%" align="center"></el-table-column>
            <el-table-column prop="svCode" label="场景码" min-width="5%" align="center"></el-table-column>
            <el-table-column prop="svName" label="场景中文名称" min-width="10%" align="center">
                <template slot-scope="scope">
                    <a style="color: #49ba9c" @click="sceneDetail(scope.row.svId)">{{scope.row.svName}}</a>
                </template>
            </el-table-column>
            <el-table-column prop="svInterface" label="关联接口" min-width="7%" align="center"></el-table-column>
            <el-table-column prop="sysName" label="系统名称" align="center" min-width="10%" ></el-table-column>
            <el-table-column label="最后更新时间" align="center" prop="svUpdateTime" min-width="10%" ></el-table-column>
            <el-table-column prop="auditState" label="审核状态" min-width="6%" :formatter="stateFormat" align="center"/>
            <el-table-column prop="svStatus" label="可用状态" min-width="5%" align="center">
                <template slot-scope="scope">
                    <span>{{scope.row.svStatus==1?'可用':'不可用'}}</span>
                </template>
            </el-table-column>
            <el-table-column prop="svType" label="场景类型" min-width="5%" align="center">
                <template slot-scope="scope">
                    <span>{{scope.row.svType==1?'服务场景':scope.row.svType==2?'主题场景':'组合服务场景'}}</span>
                </template>
            </el-table-column>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width" min-width="15%" >
                <template slot-scope="scope">
                    <apply-before
                        v-show="!scope.row.instanceId"
                        :row="scope.row"
                        :handleUpdate="handleUpdate"
                        :requestMapping="requestMapping"
                        @getList="getList"
                    ></apply-before>
                    <apply-after
                        v-show="scope.row.instanceId"
                        :row="scope.row"
                        :taskId="scope.row.taskId"
                        :type="scope.row.type"
                        @getList="getList"
                    ></apply-after>
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

        <el-dialog title="场景审核详情" :visible.sync="openLc" width="800px">
            <el-card class="box-card items mb20">
                <div slot="header" class="clearfix">
                    <span class="title">审批信息</span>
                    <el-button class="addButton" type="primary" plain @click="checkDetail()">查看场景详情</el-button>
                </div>
                <div class="text items">
                    <table class="el-table myTable" cellpadding="5" cellspacing="5" style="font-size: 16px">
                        <tr>
                            <td>申请单类型:</td>
                            <td>服务场景申请单</td>
                            <td>操作类型:</td>
                            <td>升级</td>
                        </tr>
                        <tr>
                            <td>项目编号:</td>
                            <td>BNX_SCENE_UCBP-银联卡整合交易平台系统服务场景</td>
                            <td>申请单归属系统:</td>
                            <td>银联卡整合交易平台</td>
                        </tr>
                        <tr>
                            <td>审核状态:</td>
                            <td>审核通过</td>
                            <td>创建时间:</td>
                            <td>2022-03-28 16:38:34</td>
                        </tr>
                        <tr>
                            <td>当前环节:</td>
                            <td></td>
                            <td>当前环节负责人:</td>
                            <td>yb1812-杨奔</td>
                        </tr>

                    </table>
                </div>
            </el-card>

            <el-card class="box-card items mb20">
                <div slot="header" class="clearfix">
                    <span class="title">审批记录</span>
                    <el-button class="addButton" type="primary" plain @click="checkAppro">查看审批流程图</el-button>
                </div>

                <div>
                    <el-table :data="list">
                        <el-table-column label="环节" prop="link" min-width="20%"></el-table-column>
                        <el-table-column label="审批人" align="center" prop="spr" min-width="10%"></el-table-column>
                        <el-table-column label="时间" align="center" prop="time" min-width="30%"></el-table-column>
                        <el-table-column label="审批结果" align="center" prop="spjg" min-width="20%"></el-table-column>
                        <el-table-column label="审批批注" align="center" prop="sppz" min-width="20%"></el-table-column>
                    </el-table>
                </div>
            </el-card>
        </el-dialog>

        <!--流程图-->
        <el-dialog title="审批流程" :visible.sync="splcOpen">
            <img
                style="width: 600px;"
                :src="baseURL + '/activiti/definition/readResource?pdid=leave:2:10&resourceName=C:/ruoyi/uploadPath/processDefiniton/2022/04/20/99c1000c-aab9-42be-8bae-9c378b9eace2.bpmn.leave.png'"
            />
        </el-dialog>

        <el-dialog title="删除服务场景审核失败版本" :visible.sync="open">
            <el-table
                v-loading="loading"
                :header-cell-style="{'background-color': '#eef1f6','color': 'rgb(96,98,102)','font-size':'18','border-bottom': '1px rgb(103, 194, 58) solid'}"
                :data="delList">
                <el-table-column @selection-change="handleSelectionChange" type="selection" min-width="3%"
                                 align="center"></el-table-column>
                <el-table-column prop="serviceId" label="服务编号" min-width="5%"></el-table-column>
                <el-table-column prop="serviceName" label="服务名称" min-width="15%"></el-table-column>
                <el-table-column prop="sceneNo" label="场景码" min-width="5%"></el-table-column>
                <el-table-column prop="sceneName" label="场景中文名称" min-width="15%">

                </el-table-column>
                <el-table-column prop="aboutInterface" label="关联接口" min-width="7%"></el-table-column>
                <el-table-column prop="sysName" label="系统名称" min-width="10%"></el-table-column>
            </el-table>
        </el-dialog>




		<!--查看场景详情-->
        <el-dialog title="场景详情" :visible.sync="openScene" width="1400px" @close="closeDetail">
            <el-tabs v-model="activeName">
                <el-tab-pane label="基本信息" name="first">
                    <jbxx :svId="svId" :key="timer"></jbxx>
                </el-tab-pane>
                <el-tab-pane label="场景指标" name="second">
                    <cjzb :svId="svId" :key="timer"></cjzb>
                </el-tab-pane>
                <el-tab-pane label="场景版本历史" name="third">
                    <bbls :svId="svId" :key="timer"></bbls>
                </el-tab-pane>
                <el-tab-pane label="场景定义" name="fourth">
                    <cjdy :svId="svId" :key="timer"></cjdy>
                </el-tab-pane>
                <el-tab-pane label="被消费列表" name="five">
                    <bxflb :svId="svId" :key="timer"></bxflb>
                </el-tab-pane>
            </el-tabs>
        </el-dialog>

        <!--发布-->
        <el-dialog title="场景发布" :visible.sync="releaseOpen" width="900px">
            <el-form ref="release" inline label-width="150px" :model="releaseParams" :rules="rules">
                <el-row>
                    <el-col :span="24">
                        <el-form-item label="项目编号:" prop="projectCode">
                            <el-select v-model="releaseParams.projectCode" style="width: 600px">
                                <el-option
                                    v-for="pro in projectOptions"
                                    :key="pro.projectCode"
                                    :label="pro.projectName"
                                    :value="pro.projectCode"
                                />
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :span="24">
                        <el-form-item label="是否对标:" prop="stdFieldCheck">
                            <el-select v-model="releaseParams.stdFieldCheck" style="width: 600px">
                                <el-option label="是" value="1"></el-option>
                                <el-option label="否" value="0"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :span="24">
                        <el-form-item label="是否适配:" prop="jkAdapFlag">
                            <el-select v-model="releaseParams.jkAdapFlag" style="width: 600px">
                                <el-option label="是" value="1"></el-option>
                                <el-option label="否" value="0"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :span="24">
                        <el-form-item label="是否校验:" prop="xmlValidFlag">
                            <el-select disabled v-model="releaseParams.xmlValidFlag" style="width: 600px">
                                <el-option label="是" value="1"></el-option>
                                <el-option label="否" value="0"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :span="24">
                        <el-form-item label="文件导入:">
                            <el-upload style="width: 600px"
                                       action="#"
                                       ref="upload"
                                       :limit="1"
                                       accept=".xlsx, .xls"
                                       :disabled="upload.isUploading"
                                       :auto-upload="false"
                                       :on-change="loadFile"
                                       :withCredentials="false"
                                       drag>
                                <i class="el-icon-upload"></i>
                                <div class="el-upload__text">将文件拖到此处，或<em>点击文件导入</em></div>
                            </el-upload>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="submitFileForm">确 定</el-button>
                <el-button @click="cancel">取 消</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>

    import jbxx from '@/views/manager/service/components/jbxx'
    import cjzb from '@/views/manager/service/components/cjzb'
    import bbls from '@/views/manager/service/components/bbls'
    import bxflb from '@/views/manager/service/components/bxflb'
    import cjdy from '@/views/manager/service/components/cjdy'

    import ApplyBefore from "@/components/Activiti/ApplyBefore/index";
    import ApplyAfter from "@/components/Activiti/ApplyAfter/index";

    import {getToken} from "@/utils/auth";

    import {sceneList, delSceneVersion,sceneRelease,getServiceSceneInfo} from "@/api/service/oprscenario";
    import {listProject} from "@/api/project/project";
    import {listSystem} from "@/api/system/list"
    import XLSX from "xlsx";

    export default {
        name: "service",
        components: {jbxx, cjzb, bbls, bxflb, cjdy,ApplyBefore,ApplyAfter},
        props: {
			serviceId:{
				type:String,
				default:''
			}
		},
        data() {
            return {
                datetimes: '',
                baseURL: process.env.VUE_APP_BASE_API,
                ids: '',
                svId: '',
                timer: '',
                // 非单个禁用
                single: true,
                // 非多个禁用
                multiple: true,
                // 遮罩层
                loading: true,
                // 显示搜索条件
                showSearch: true,
                // 表格树数据
                delList: [],
                list: [
                    {'link': '提交审批', 'spr': '翁羽', 'time': '2019-10-19 16：38：34', 'spjg': '批准', 'sppz': '变更原因：应答字段更新必输'},
                    {'link': '消费方系统负责人', 'spr': '徐喆', 'time': '2019-10-19 17：04：29', 'spjg': '批准', 'sppz': ''}
                ],
                senceList: [],
                total: 0,
                multipleSelection: [], // 表格被选中的内容

                // 弹出层标题
                title: "",

                releaseTitle: "",
                releaseOpen: false,

                sysOptions: [],
                projectOptions: [],

                // 是否显示弹出层
                open: false,
                openLc: false,
                splcOpen: false,
                openScene: false,

                // 是否展开，默认全部展开
                isExpandAll: true,
                // 重新渲染表格状态
                refreshTable: true,
                // 查询参数
                queryParams: {
                    startTime: null,
                    endTime: null,
                    pageNum: 1,
                    pageSize: 10,
                    serviceId: '',
					auditState:undefined,
                    svName: ''
                },
                releaseParams: {
                    stdFieldCheck: '',//对标
                    jkAdapFlag: '',//适配
                    xmlValidFlag: '1',//校验
                    projectCode:''
                },
                requestMapping: 'oprScene',
                form:{},

                groupQuery: null,

                activeName: 'first',
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
                    url: process.env.VUE_APP_BASE_API + "/oprScene/upload",
                },

                // 审核状态字典
                auditStateOptions: [],
                // 表单校验
                rules: {
                    projectCode: [
                        {required: true, message: "项目编号不能为空", trigger: ["change","blur"]}
                    ],
                    stdFieldCheck: [
                        {required: true, message: "请选择是否对标", trigger: ["change","blur"]}
                    ],
                    jkAdapFlag: [
                        {required: true, message: "请选择是否适配", trigger: ["change","blur"]}
                    ],
                    xmlValidFlag: [
                        {required: "true", message: "'请选择是否校验", trigger:["change","blur"]}
                    ]
                }
            };
        },
        created() {
            this.getDicts("aesb_audit_state").then(response => {
                this.auditStateOptions = response.data;
            });
            this.getSysOptions();
            this.getProjectOptions();
            this.getList();
        },
        methods: {
            closeDetail() {
                this.activeName = 'first'
            },
            changeTime(value) {
                if (value) {
                    this.queryParams.startTime = value[0];
                    this.queryParams.endTime = value[1];
                } else {
                    this.queryParams.startTime = null;
                    this.queryParams.endTime = null;
                }
            },
            // 审核状态字典翻译
            stateFormat(row, column) {
                return this.selectDictLabel(this.auditStateOptions, row.auditState);
            },
            //单元格样式
            cellStyle(row , colum , rowIndex , columnIndex) {
                if (row.row.auditState == "2"&& row.column.label == '审核状态') {
                    return "color : #13ce66;font-weight:500";
                }
                if (row.row.auditState == "0"&& row.column.label == '审核状态') {
                    return "color : #ffba00;font-weight:500";
                }
                if (row.row.auditState == "1"&& row.column.label == '审核状态') {
                    return "color : #1890ff;font-weight:500";
                }
                if (row.row.auditState == "3"&& row.column.label == '审核状态') {
                    return "color : #ff2c50;font-weight:500";
                }
            },
            //场景详情
            sceneDetail(svId) {
                var that = this
                that.timer = new Date().getTime()
                that.svId = svId;
                that.openScene = true
            },

            handleClickTableRow(row, column, event) {
                if (column.label === '场景中文名称' || column.label === '操作') {
                    return;
                }
                this.$refs.fileTable.toggleRowSelection(row);
            },
            // 多选框选中数据
            handleSelectionChange(selection) {
                this.ids = selection.map(item => item.svId);
                this.single = selection.length != 1;
                this.multiple = !selection.length;
            },

            // 文件上传中处理
           /* handleFileUploadProgress(event, file, fileList) {
                this.upload.isUploading = true;
            },*/
            // 文件上传成功处理
           /* handleFileSuccess(response, file, fileList) {
                this.upload.open = false;
                this.upload.isUploading = false;
                this.$refs.upload.clearFiles();
                this.$alert("<div style='overflow: auto;overflow-x: hidden;max-height: 70vh;padding: 10px 20px 0;'>" + response.msg + "</div>", "导入结果", {dangerouslyUseHTMLString: true});
                this.getList();
            },*/
            loadFile(file, fileList) {
                this.upload_file = fileList[0].raw;
                const reader = new FileReader();
                //前三sheet表
                const formData = [];
                //场景字段
                const lastData = [];
                reader.readAsBinaryString(this.upload_file);
                reader.onload = ev => {
                    try {
                        const f = ev.target.result;
                        const workbook = XLSX.read(f, {type: "binary"}, {header:0});
                        //前三sheet表
                        for (let i = 0; i < 3; i++) {
                            formData.push(XLSX.utils.sheet_to_json(workbook.Sheets[workbook.SheetNames[i]],{range:1}))
                        }
                        lastData.push(XLSX.utils.sheet_to_json(workbook.Sheets[workbook.SheetNames[3]],{range:1}))
                        //服务分类
                        const serviceData = formData[0].map((item)=>{
                            return {
                                serviceHeadClass: item['一级分类'],
                                serviceSmallClass: item['二级分类'],
                                svcCode: item['服务ID'],
                                svcName: item['服务名称']
                            }
                        })

                        //服务场景
                        const svSceneData = formData[1].map((item)=>{
                            return {
                                svCommunicationProtocol: item['通讯协议'],
                                svCode: item['服务场景码'],
                                svName: item['服务场景名称'],
                                svInterface: item['接口交易码'],
                                svMessageFormat: item['报文格式'],
                                svType: item['场景类型'],
                                updateReason: item['变更原因'],
                                updateContent: item['变更内容'],
                                sysName: item['交易提供者'],
                                svHttpUrl: item['HTTP-URL'],

                                svcCode: item['服务ID'],
                                svcName: item['服务名称'],
                                stdFieldCheck:this.releaseParams.stdFieldCheck,//对标
                                jkAdapFlag:this.releaseParams.jkAdapFlag,//适配
                                xmlValidFlag:this.releaseParams.xmlValidFlag,//校验
                                projectCode:this.releaseParams.projectCode
                            }
                        })
                        //场景指标
                        const svParamsData = formData[2].map((item)=>{
                            return {
                                svCode: item['服务场景码'],
                                svName: item['服务场景名称'],
                                sceneTimeout: item['服务超时时间']
                            }
                        })
                        //场景字段
                        let num = 0;
                        for (let i = 0; i < lastData[0].length; i++) {
                            if (lastData[0][i].英文名称==='服务场景字段（输出）'){
                                num = i+2;
                                break;
                            }
                        }
                        //输入
                        const inData=[]
                        //输出
                        const outData=[]
                        for (let i = 0; i < num-2; i++) {
                            inData.push(lastData[0][i])
                        }
                        for (let i = num; i < lastData[0].length; i++) {
                            outData.push(lastData[0][i])
                        }

                        var newInData = inData.map(item => ({
                            sfEnName: item['英文名称'],
                            sfCnName: item['中文名称'],
                            sfDataType: item['数据类型'],
                            sfPath: item['字段位置'],
                            sfLength: item['字段长度（BYTE）'],
                            sfFormat: item['数据格式'],
                            sfIsEnssential: item['是否必输'],
                            sfIsSensitive: item['是否敏感字段'],
                            sfLengthType: item['长度类型']
                        }))
                        var newOutData = outData.map(item => ({
                            sfEnName: item['英文名称'],
                            sfCnName: item['中文名称'],
                            sfDataType: item['数据类型'],
                            sfPath: item['字段位置'],
                            sfLength: item['字段长度（BYTE）'],
                            sfFormat: item['数据格式'],
                            sfIsEnssential: item['是否必输'],
                            sfIsSensitive: item['是否敏感字段'],
                            sfLengthType: item['长度类型']
                        }))

                        this.form = {
                            serviceData:serviceData,
                            svSceneData:svSceneData,
                            svParamsData:svParamsData,
                            sceneFieldData:{
                                newInData:newInData,
                                newOutData:newOutData
                            }
                        }
                    } catch (e) {
                        console.log(e);
                        return false;
                    }
                };
            },
            /** 系统列表 */
            getSysOptions() {
                listSystem().then(res => {
                    this.sysOptions = res.rows
                })
            },
            /** 项目列表 */
            getProjectOptions() {
                listProject().then(res => {
                    this.projectOptions = res.rows
                })
            },

            /** 查询服务场景列表 */
            getList() {
                this.loading = true;
                this.queryParams.serviceId = this.serviceId;
                sceneList(this.queryParams).then(res => {
                    this.senceList = res.rows;
                    this.total = res.total;
                    this.loading = false
                })
            },
            // 取消按钮
            cancel() {
                this.releaseOpen = false;
                this.reset();
            },
            // 表单重置
            reset() {
                this.releaseParams = {
                    projectCode: undefined,
                    stdFieldCheck: undefined,
                    jkAdapFlag: undefined,
                    xmlValidFlag: "1",
                };
                this.resetForm("release");
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


            /** 提交按钮 */
            submitFileForm() {
                this.$refs["release"].validate(valid => {
                    if (valid) {
                        sceneRelease(this.form).then(response => {
                            console.log(response)
                            this.msgSuccess("发布成功");
                            this.releaseOpen = false;
                            this.getList();
                        });
                    }
                });
            },
            /** 删除按钮操作 */
            handleDelete(row) {
                const svIds = this.ids;
                this.$confirm('是否确认删除名称为"' + svIds + '"的数据项？').then(function () {
                    return delSceneVersion(svIds);
                }).then(() => {
                    this.getList();
                    this.msgSuccess("删除成功");
                }).catch(() => {
                });
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
				this.$router.push({path: `/service/add`, query: {svId: row.svId}})
            },
            checkStatue() {
                this.openLc = true;
            },
            handleCommand(e, row) {
                if (e === 'handleDelVersion') {
                    this.open = true
                }
            },

            /**
             * 场景详情
             */
            checkDetail() {
                this.openScene = true
            },
            checkAppro() {
                this.splcOpen = true;
            },
            //发布
            release() {
                this.releaseOpen = true;
                this.releaseTitle = "场景发布"
            },
            //场景编辑
            handleEdit() {
                const ids = this.ids
                this.$router.push({path: `/service/add`, query: {svId: ids}})
            },
            //查看详情
            handleDetail() {
                const ids = this.ids
                this.timer = new Date().getTime()
                this.svId = ids[0];
                this.openScene = true
            }
        }

    };
</script>

<style scoped lang="scss">

    .dialog-footer {
        text-align: center;
        margin-bottom: 10px;
    }

    .auditStatue {
        background-color: #e6f8f8;
        border-radius: 3%;
        color: #07b6b5;
        padding: 5px
    }

    .auditStatue:hover {
        cursor: pointer;
    }

    .clearfix:before,
    .clearfix:after {
        display: table;
        content: "";
    }

    .clearfix:after {
        clear: both
    }

    .box-card {
        width: 100%;
    }

    .items .el-card__header {
        background: #f5f7fa;
        padding-bottom: 5px;
    }

    .title {
        color: #303133;
        font-weight: bold;
    }

    /**按钮样式*/
    .addButton {
        float: right;
        position: relative;
        z-index: 1;
        transform: translateY(-5px);
    }

    .myTable {
        width: 100%;
        text-align: center;
    }

    .myTable td:nth-child(even) { //偶数列
        color: rgb(40, 40, 40);
        font-size: 15px;
        width: 30%;
        border-bottom: 1px solid #4ab7bd;
    }

    .myTable td:nth-child(odd) { //奇数列
        color: rgb(40, 40, 40);
        font-weight: bold;
        font-size: 15px;
        width: 20%;
    }

</style>
