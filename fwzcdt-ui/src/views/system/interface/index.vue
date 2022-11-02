<template>
    <div class="app-container">
        <el-form :model="queryParams" ref="query" label-width="150px">
            <el-row>
                <el-col :span="6">
                    <el-form-item label="模糊查询:" class="search">
                        <el-input size="small" style="width: 300px" v-model="queryParams.keywords" placeholder="请输入关键字"/>
                    </el-form-item>
                </el-col>
                <el-col :span="6">
                    <el-form-item label="所属系统:" class="search">
                        <el-select style="width: 300px" v-model="queryParams.belowSystem" size="small">
                            <el-option label="ZH14-测试" value="ZH14"></el-option>
                        </el-select>
                    </el-form-item>

                </el-col>
                <el-col :span="6">
                    <el-form-item label="状态:" class="search">
                        <el-select style="width: 300px"  v-model="queryParams.status" size="small">
                            <el-option
                                v-for="dict in stateOptions"
                                :key="dict.dictValue"
                                :label="dict.dictLabel"
                                :value="dict.dictValue"
                            />
                        </el-select>
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>
        <!--菜单按钮-->
        <div style="float: right;position: relative;z-index: 11;">
            <el-row :gutter="12" class="mb8">
                <el-col :span="1.5">
                    <el-button type="warning" plain icon="el-icon-search" size="small" @click="handleQuery">查询</el-button>
                </el-col>
                <el-col :span="1.5">
                    <el-button plain icon="el-icon-refresh" size="small" @click="resetQuery">重置</el-button>
                </el-col>
                <el-col :span="1.5">
                    <el-button type="primary" plain icon="el-icon-plus" size="small" @click="handleAdd(0)">新增</el-button>
                </el-col>
                <el-col :span="1.5">
                    <el-button type="success" plain icon="el-icon-edit" size="small" :disabled="single" @click="handleUpdate">修改</el-button>
                </el-col>
                <el-col :span="1.5">
                    <el-button type="danger" plain icon="el-icon-delete" size="small" :disabled="multiple" @click="handleDelete">删除</el-button>
                </el-col>
                <el-col :span="1.5">
                    <el-button type="default" plain icon="el-icon-download" size="small" @click="handleImport">导入</el-button>
                </el-col>
                <el-col :span="1.5">
                    <el-button type="warning" plain icon="el-icon-upload2" size="small" @click="handleExport">导出</el-button>
                </el-col>
            </el-row>
        </div>
        <!--表格-->
        <el-row>
            <el-col>
                <el-table ref="table" v-loading="loading" :data="interfaceList" @selection-change="handleSelectionChange"
                          :cell-style="cellStyle" highlight-current-row @row-click="handleClickTableRow" :row-class-name="rowIndex">
                    <el-table-column type="selection" width="55" align="center"/>
                    <el-table-column :formatter="order" label="序号" align="center" width="50"/>
                    <el-table-column label="接口码" align="center" prop="tradeCode"/>
                    <el-table-column label="接口名称" align="center" prop="interfaceName">
                        <template slot-scope="scope">
                            <a @click="getDetail">{{scope.row.jkNameCn}}</a>
                        </template>
                    </el-table-column>
                    <el-table-column label="所属系统" align="center" prop="sysName"/>
                    <el-table-column label="接口版本名" align="center" prop="jkVersionName"/>
               <!--     <el-table-column label="报文类型" align="center" prop="msgType" />-->
                    <el-table-column label="投产状态" align="center" prop="deliverState">
                        <template slot-scope="scope">
                            <span>{{scope.row.deliverState==0?'设计态':'投产态'}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column label="状态" align="center" :formatter="stateFormatter">
                        <template slot-scope="scope">
                            <el-button type="success" @click="openProcess">{{stateFormatter(scope.row,scope.column)}}</el-button>
                        </template>
                    </el-table-column>

                    <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                        <template slot-scope="scope">
                            <el-button
                                size="mini"
                                type="text"
                                icon="el-icon-edit"
                                @click="handleUpdate(scope.row)"
                            >编辑
                            </el-button>
                            <el-button
                                size="mini"
                                type="text"
                                icon="el-icon-delete"
                                @click="handleDelete(scope.row)"
                            >删除
                            </el-button>
                            <el-dropdown size="mini" @command="(command) => handleCommand(command, scope.row)">
                        <span class="el-dropdown-link">
                          <i class="el-icon-d-arrow-right el-icon--right"></i>更多
                        </span>
                                <el-dropdown-menu slot="dropdown">
                                    <el-dropdown-item command="handleDownLine" icon="el-icon-key">下线</el-dropdown-item>
                                    <el-dropdown-item command="handleDelVersion" icon="el-icon-circle-check">删除废弃版本</el-dropdown-item>
                                </el-dropdown-menu>
                            </el-dropdown>
                        </template>
                    </el-table-column>
                </el-table>
            </el-col>
            <el-col>
                <pagination
                    v-show="total>0"
                    :total="total"
                    :page.sync="queryParams.pageNum"
                    :limit.sync="queryParams.pageSize"
                    @pagination="getList"
                />
            </el-col>
        </el-row>
        <!--添加修改对话框-->
        <el-row>
            <el-dialog :modal-append-to-body="true" width="80%" :title="title"  :visible.sync="open">
                <el-form ref="form" :model="form" :rules="rules" label-width="100px">
                    <el-card class="box-card items mb20">
                        <div slot="header" class="clearfix">
                            <span class="title">接口信息</span>
                        </div>
                        <div class="text item">
                            <el-row :gutter="10">
                                <el-col :span="8">
                                    <el-form-item label="所属系统:" required>
                                        <el-select v-model="form.system" style="width: 350px">
                                            <el-option label="ZK04-测试" value="ZK04"></el-option>
                                            <el-option label="ZK03-测试" value="ZK03"></el-option>
                                            <el-option label="ZK02-测试" value="ZK02"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="所属项目:" required>
                                        <el-select v-model="form.project" style="width: 350px">
                                            <el-option label="LABOR-泸州银行poc" value="LABOR"></el-option>
                                            <el-option label="pocSDBank-首都银行POC" value="pocSDBank"></el-option>
                                            <el-option label="LATEST-测试" value="LATEST"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="接口码:" required>
                                        <el-input style="width: 350px;" v-model="form.interfaceCode" placeholder="请输入接口码" />
                                    </el-form-item>
                                </el-col>
                            </el-row>
                            <el-row :gutter="15">
                                <el-col :span="8">
                                    <el-form-item label="接口名称:" required>
                                        <el-input style="width: 350px;" v-model="form.interfaceName" placeholder="请输入接口名称" />
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="报文类型:" required>
                                        <el-select v-model="form.msgType" style="width: 350px">
                                            <el-option label="XML" value="0"></el-option>
                                            <el-option label="FIX" value="1"></el-option>
                                            <el-option label="SOP" value="2"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="接口描述:" required>
                                        <el-input style="width: 350px;" v-model="form.remark" placeholder="请输入接口描述" />
                                    </el-form-item>
                                </el-col>
                            </el-row>
                        </div>
                    </el-card>
                </el-form>

                <el-form ref="inForm" :model="inForm" :rules="inRules" label-width="120px">
                    <!--接口输入字段-->
                    <el-dialog width="40%" :title="inTitle" :visible.sync="openIn" :append-to-body="true">
                        <el-row :gutter="5">
                            <el-col :span="12">
                                <el-form-item label="字段英文名" required>
                                    <el-input v-model="inForm.enName" placeholder="请输入字段英文名"/>
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="字段英文名" required>
                                    <el-input v-model="inForm.znName" placeholder="请输入字段中文名"/>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row :gutter="10">
                            <el-col :span="12">
                                <el-form-item label="字段路径" required>
                                    <el-input v-model="inForm.filedPath" placeholder="请输入字段路径"/>
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="字段类型">
                                    <el-select style="width: 100%" v-model="inForm.filedType">
                                        <el-option label="字符型" value="0"/>
                                        <el-option label="整型" value="1"/>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row :gutter="10">
                            <el-col :span="12">
                                <el-form-item label="是否必输">
                                    <el-select style="width: 100%" v-model="inForm.isRequired">
                                        <el-option label="否" value="0"/>
                                        <el-option label="是" value="1"/>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="是否敏感字段">
                                    <el-select style="width: 100%" v-model="inForm.isSensitive">
                                        <el-option label="否" value="0"/>
                                        <el-option label="是" value="1"/>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row :gutter="10">
                            <el-col :span="12">
                                <el-form-item label="默认值">
                                    <el-input v-model="inForm.defaultValue" placeholder="请输入默认值"/>
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="字段长度">
                                    <el-input v-model="inForm.filedLength" placeholder="请输入字段长度"/>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row :gutter="10">
                            <el-col :span="12">
                                <el-form-item label="循环次数">
                                    <el-input v-model="inForm.cycles" placeholder="请输入循环次数"/>
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="命名空间">
                                    <el-input v-model="inForm.nameSpace" placeholder="请输入命名空间"/>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row :gutter="10">
                            <el-col :span="12">
                                <el-form-item label="枚举值">
                                    <el-input type="textarea" :maxlength="200" show-word-limit v-model="inForm.enumValue" placeholder="请输入枚举值"/>
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="字段描述">
                                    <el-input type="textarea" :maxlength="200" show-word-limit v-model="inForm.filedRemark" placeholder="请输入字段描述"/>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <div slot="footer" class="dialog-footer">
                            <el-button type="primary" @click="submitIn">确 定</el-button>
                            <el-button @click="cancel(1)">取 消</el-button>
                        </div>
                    </el-dialog>
                    <el-card class="box-card items mb20">
                        <div slot="header" class="clearfix">
                            <span class="title">接口输入字段</span>
                            <el-button class="addButton" @click="handleAdd(1)" type="primary" size="small">新增</el-button>
                        </div>
                        <div class="text item">
                            <el-table ref="inTable" :data="interfaceInList">
                                <el-table-column label="英文名" align="center" prop="enName"/>
                                <el-table-column label="中文名" align="center" prop="znName"/>
                                <el-table-column label="字段类型" align="center" prop="filedType"/>
                                <el-table-column label="是否必输" align="center" prop="isRequired"/>
                                <el-table-column label="是否敏感" align="center" prop="isSensitive"/>
                                <el-table-column label="字段长度" align="center" prop="filedLength"/>
                                <el-table-column label="默认值" align="center" prop="defaultValue"/>
                                <el-table-column label="字段描述" align="center" prop="filedRemark"/>
                                <el-table-column label="枚举值" align="center" prop="enumValue"/>
                                <el-table-column label="循环次数" align="center" prop="cycles"/>
                                <el-table-column label="字段路径" align="center" prop="filedPath"/>
                                <el-table-column label="命名空间" align="center" prop="nameSpace"/>
                                <el-table-column label="操作" align="center">
                                    <template slot-scope="scope">
                                        <el-button
                                            size="mini"
                                            type="text"
                                            icon="el-icon-edit"
                                            @click="handleEdit(scope.row,'in')"
                                        >编辑
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
                        </div>
                    </el-card>
                </el-form>

                <el-form ref="outForm" :model="outForm" :rules="outRules" label-width="120px">
                    <!--接口输出字段-->
                    <el-dialog width="40%" :title="outTitle" :visible.sync="openOut" :append-to-body="true" modal-append-to-body>
                        <el-row :gutter="10">
                            <el-col :span="12">
                                <el-form-item label="字段英文名" required>
                                    <el-input v-model="outForm.enName" placeholder="请输入字段英文名"/>
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="字段英文名" required>
                                    <el-input v-model="outForm.znName" placeholder="请输入字段中文名"/>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row :gutter="10">
                            <el-col :span="12">
                                <el-form-item label="字段路径" required>
                                    <el-input v-model="outForm.filedPath" placeholder="请输入字段路径"/>
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="字段类型">
                                    <el-select style="width: 100%" v-model="outForm.filedType">
                                        <el-option label="字符型" value="0"/>
                                        <el-option label="整型" value="1"/>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row :gutter="10">
                            <el-col :span="12">
                                <el-form-item label="是否必输">
                                    <el-select style="width: 100%" v-model="outForm.isRequired">
                                        <el-option label="否" value="0"/>
                                        <el-option label="是" value="1"/>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="是否敏感字段">
                                    <el-select style="width: 100%" v-model="outForm.isSensitive">
                                        <el-option label="否" value="0"/>
                                        <el-option label="是" value="1"/>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row :gutter="10">
                            <el-col :span="12">
                                <el-form-item label="默认值">
                                    <el-input v-model="outForm.defaultValue" placeholder="请输入默认值"/>
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="字段长度">
                                    <el-input v-model="outForm.filedLength" placeholder="请输入字段长度"/>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row :gutter="10">
                            <el-col :span="12">
                                <el-form-item label="循环次数">
                                    <el-input v-model="outForm.cycles" placeholder="请输入循环次数"/>
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="命名空间">
                                    <el-input v-model="outForm.nameSpace" placeholder="请输入命名空间"/>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row :gutter="10">
                            <el-col :span="12">
                                <el-form-item label="枚举值">
                                    <el-input type="textarea" :maxlength="200" show-word-limit v-model="outForm.enumValue" placeholder="请输入枚举值"/>
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="字段描述">
                                    <el-input type="textarea" :maxlength="200" show-word-limit v-model="outForm.filedRemark" placeholder="请输入字段描述"/>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <div slot="footer" class="dialog-footer">
                            <el-button type="primary" @click="submitOut">确 定</el-button>
                            <el-button @click="cancel(2)">取 消</el-button>
                        </div>
                    </el-dialog>
                    <el-card class="box-card items mb20">
                        <div slot="header" class="clearfix">
                            <span class="title">接口输出字段</span>
                            <el-button class="addButton" @click="handleAdd(2)" type="primary" size="small">新增</el-button>
                        </div>
                        <div class="text item">
                            <el-table ref="outTable" :data="interfaceOutList">
                                <el-table-column label="英文名" align="center" prop="enName"/>
                                <el-table-column label="中文名" align="center" prop="znName"/>
                                <el-table-column label="字段类型" align="center" prop="filedType"/>
                                <el-table-column label="是否必输" align="center" prop="isRequired"/>
                                <el-table-column label="是否敏感" align="center" prop="isSensitive"/>
                                <el-table-column label="字段长度" align="center" prop="filedLength"/>
                                <el-table-column label="默认值" align="center" prop="defaultValue"/>
                                <el-table-column label="字段描述" align="center" prop="filedRemark"/>
                                <el-table-column label="枚举值" align="center" prop="enumValue"/>
                                <el-table-column label="循环次数" align="center" prop="cycles"/>
                                <el-table-column label="字段路径" align="center" prop="filedPath"/>
                                <el-table-column label="命名空间" align="center" prop="nameSpace"/>
                                <el-table-column label="操作" align="center">
                                    <template slot-scope="scope">
                                        <el-button
                                            size="mini"
                                            type="text"
                                            icon="el-icon-edit"
                                            @click="handleEdit(scope.row,'out')"
                                        >编辑
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
                        </div>
                    </el-card>
                </el-form>

                <div slot="footer" class="dialog-footer">
                    <el-button type="primary" @click="submitForm">确 定</el-button>
                    <el-button @click="cancel(0)">取 消</el-button>
                </div>
            </el-dialog>
        </el-row>

        <!--详情对话框-->
        <el-dialog width="80%" title="接口详情" :visible.sync="openDetail">
            <interface-info></interface-info>
        </el-dialog>

        <!--审批流程对话框-->
        <el-dialog width="70%" title="审批详情" :visible.sync="openApproval">
            <approval></approval>
        </el-dialog>

        <!--下线-->
        <el-dialog width="30%" title="接口下线" :visible.async="downLine" close-on-click-modal>
            <el-form ref="downLine" :model="form">
                <el-form-item label="选则项目:">
                    <el-select v-model="form.system">
                        <el-option label="" value=""/>
                    </el-select>
                </el-form-item>
                <el-form-item label="确定下线:">
                    <span>余额查询</span>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="submitForm">确 定</el-button>
                <el-button @click="cancel(3)">取 消</el-button>
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
    import {getToken} from "@/utils/auth";
    import approval from '@/views/system/interface/components/approval'
    import interfaceInfo from '@/views/system/interface/components/interfaceInfo'
    import {listSysinter} from "@/api/sysinter/sysinter";

    export default {
        name: "index",
        components: {approval,interfaceInfo},
        data(){
            return{
                // 状态数据字典
                stateOptions: [],
                //接口类型字典
                typeOptions:[],

                //遮罩层
                loading:false,//表格查询遮罩层
                inLoading:false,//接口输入字段表格遮罩层

                //对话框参数设置
                open:false,
                openIn:false,
                openOut:false,
                openDetail:false,
                openApproval:false,
                downLine:false,//下线
                title:'',
                inTitle:'',
                outTitle:'',

                // 导入参数
                upload: {
                    // 是否显示弹出层（导入）
                    open: false,
                    /*// 弹出层标题（导入）
                    title: "",*/
                    // 是否禁用上传
                    isUploading: false,
                 /*   // 是否更新已经存在的用户数据
                    updateSupport: 0,*/
                    // 设置上传的请求头部
                    headers: {Authorization: "Bearer " + getToken()},
                    // 上传的地址
                    url: process.env.VUE_APP_BASE_API + ""
                },

                //表格数据
                interfaceList:[],
                interfaceInList:[],
                interfaceOutList:[],

                //查询参数
                queryParams:{
                    pageNum:1,
                    pageSize:10
                },
                //表单数据
                form:{},
                inForm:{},
                outForm:{},
                total:3,

                // 选中数组
                ids: [],
                // 非单个禁用
                single: true,
                // 非多个禁用
                multiple: true,
                //校验
                rules:{},
                inRules:{},
                outRules:{}
            }
        },
        created() {
            this.getDicts("interface_state").then(response => {
                this.stateOptions = response.data;
            });
            this.getDicts("interface_type").then(response => {
                this.typeOptions = response.data;
            });

            this.getList();
        },
        methods:{
            //查询
            handleQuery(){
            },
            //重置
            resetQuery(){},
            getList(){
                this.loading = true
                listSysinter(this.queryParams).then(res=>{
                    this.interfaceList = res.rows;
                    this.total = res.total
                    this.loading = false
                })

            },
            // 状态字典翻译
            stateFormatter(row, column){
                return this.selectDictLabel(this.stateOptions, row.state);
            },
            //投产状态翻译
            tcStateFormatter(row,column){
                return this.selectDictLabel(this.stateOptions, row.state);
            },

            // 多选框选中数据
            handleSelectionChange(selection) {
                this.ids = selection.map(item => item.userId)
                this.single = selection.length != 1
                this.multiple = !selection.length
            },
            handleClickTableRow(row,column,event) {
                if (!this.openDetail && !this.openApproval){
                    if(column.label=='操作'){
                        return;
                    }
                    this.$refs.table.toggleRowSelection(row);
                }

            },
            //样式
            cellStyle(row,column,rowIndex,columnIndex){//根据报警级别显示颜色
                if(row.column.label==="接口名称"){
                    return 'color:#49ba9c';
                }
            },
            //查看详情
            getDetail(){
                this.openDetail = true
            },
            //新增
            handleAdd(number){
                //判断number新增
                if (number===0){
                    this.title = '新增接口'
                    this.open = true
                }
                if (number===1){
                    this.inTitle = '新增字段'
                    this.openIn = true
                }
                if (number===2){
                    this.outTitle = '新增字段'
                    this.openOut = true
                }
            },
            //修改/编辑
            handleUpdate(){
                this.title = '编辑接口'
                this.open = true
            },
            //修改接口数据
            handleEdit(row,type){
                if (type==='in'){
                    this.inTitle = '编辑字段'
                    this.openIn = true
                }
                if(type==='out'){
                    this.outTitle = '编辑字段'
                    this.openOut = true
                }
            },
            //删除
            handleDelete(row){
                const userIds = row.rowIndex+1 || this.ids;
                this.$modal.confirm('确定删除"'+userIds+'"的数据项？').then(function () {
                    /*return delUser(userIds);*/
                }).then(() => {
                    //this.getList();
                    this.$modal.msgSuccess("删除成功!");
                }).catch(() => {
                    this.$modal.msgError("操作失败!");
                });
            },
            //导入
            handleImport(){
                this.upload.open=true
            },
            //导出
            handleExport(){
                this.download('system/user/export', {
                    ...this.queryParams
                }, `interface_${new Date().getTime()}.xlsx`)
            },
            //更多操作
            handleCommand(type,row){
                console.log(row)
                if (type==='handleDownLine'){
                    this.downLine = true
                }
                if (type==='handleDelVersion'){
                    this.$modal.confirm('确定删除选中接口的最新已废弃版本吗？').then(function () {
                        /*return delUser(userIds);*/
                    }).then(() => {
                        //this.getList();
                        this.$modal.msgSuccess("删除成功!");
                    }).catch(() => {
                        this.$modal.msgError("操作失败!");
                    });
                }
            },

            //接口输出入字段 对话框提交
            submitIn(){
                var that = this
                this.$refs["inForm"].validate(valid=>{
                    if (valid){
                        this.interfaceInList.push(that.inForm);
                    }
                })
                this.openIn = false;
            },
            submitOut(){
                var that = this
                this.$refs["outForm"].validate(valid=>{
                    if (valid){
                        this.interfaceOutList.push(that.outForm);
                    }
                })
                this.openOut = false;
            },

            //取消按钮
            cancel(number){
                if (number===0){
                    this.open = false
                }
                if (number===1){
                    this.openIn = false
                }
                if (number===2){
                    this.openOut = false
                }
                if (number===3){
                    this.downLine = false
                }
            },
            //表单提交
            submitForm(){},
            //审批流程
            openProcess(){
                this.openApproval = true
            },
            rowIndex({row, rowIndex}) {
                row.rowIndex = rowIndex;
            },
            order(row) {
                return this.queryParams.pageSize * (this.queryParams.pageNum - 1) + row.rowIndex + 1;
            },
            /** 下载模板操作 */
            importTemplate() {
                this.download('system/user/importTemplate', {}, `user_template_${new Date().getTime()}.xlsx`)
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
            }

        }
    }
</script>

<style>
    .search .el-form-item__label{
        color: #606266;
        font-size: 15px;
        font-weight: 500;
    }
    .text {
        font-size: 14px;
    }

    .item {
        margin-bottom: 18px;
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
    .items .el-card__header{
        background: #f5f7fa;
        padding-bottom: 5px;
    }
    .title{
        color: #303133;
        font-weight: bold;
    }
    /**按钮样式*/
    .addButton{
        float: right;
        position: relative;
        z-index: 1;
        transform: translateY(-5px);
    }
</style>
