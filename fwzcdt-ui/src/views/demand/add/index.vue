<template>
    <div class="app-container">
        <!--顶部步骤条-->
        <el-steps align-center :active="num" finish-status="success">
            <el-step title="录入" icon="el-icon-edit"></el-step>
            <el-step title="分解" icon="el-icon-s-ticket"></el-step>
            <el-step title="派送" icon="el-icon-position"></el-step>
        </el-steps>
		<!--导入-->
        <div v-show="num===0" class="mt20 mb20">
            <!--录入方式区域-->
            <div style="text-align: center">
                <el-radio-group v-model="radio" @change="handleChange">
                    <el-radio  border :label="1">Excel导入</el-radio>
                    <el-radio  border :label="2">手动录入</el-radio>
                </el-radio-group>

            </div>
            <!--导入按钮  下一步-->
            <div class="mt20 mb20" v-if="radio===1">
                <el-upload style="text-align: center"
                           :action="upload.url"
                           ref="upload"
                           :limit="1"
                           accept=".xlsx, .xls"
                           :disabled="upload.isUploading"
                           :auto-upload="true"
                           :on-progress="handleFileUploadProgress"
                           :on-success="handleFileSuccess"
                           :on-change="loadFile"
                           :withCredentials="false"
                drag>
                    <i class="el-icon-upload"></i>
                    <div class="el-upload__text">将文件拖到此处，或<em>点击文件导入</em></div>
                </el-upload>
            </div>
        </div>
        <!--需求表单-->
        <div class="need-form" v-show="num===1">
            <el-divider />
            <el-form :model="form" ref="form" :rules="rules" label-width="108px">
                <el-row :gutter="10">
                    <el-col :span="12">
                        <el-form-item label="需求编号：">
                            <el-input placeholder="请输入内容" readonly v-model="form.demandId" class="input-with-select" :disabled="radio===1">
                                <el-switch
                                    :disabled="radio===1"
                                    @change="AutoGenerate"
                                    slot="append"
                                    v-model="form.isAutoGet"
                                    active-text="自动生成">
                                </el-switch>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="需求名称：">
                            <el-input :disabled="radio===1"  v-model="form.demandName"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="10">
                    <el-col :span="12">
                        <el-form-item label="需求描述：">
                            <el-input type="textarea" v-model="form.demandDes" :rows="4"/>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="10">
                    <el-col :span="12">
                        <el-form-item label="实施周期：">
                            <el-date-picker
                                v-model="range"
                                type="daterange"
                                value-format="yyyy-MM-dd"
                                range-separator="至"
                                start-placeholder="开始日期"
                                end-placeholder="结束日期">
                            </el-date-picker>
                        </el-form-item>

                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="申请日期：">
                            <el-date-picker
                                v-model="form.applyTime"
                                type="date"
                                value-format="yyyy-MM-dd"
                                placeholder="选择日期">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-row>
                <div class="need-submit">
                    <el-row :gutter="20">
                        <el-col :span="12" align="center">
                            <el-button style="width: 30%" type="primary" :disabled="demandState>=1" size="middle" @click="submit">保存</el-button>
                        </el-col>
                        <el-col :span="12" align="center">
                            <el-button style="width: 30%" type="primary" size="middle"  :disabled="demandState>=1" @click="breakDown" v-show="showBreak">分解</el-button>
                        </el-col>
                    </el-row>
                </div>
            </el-form>
        </div>

		<!--关联系统选择-->
        <div class="need-form" style="color: #686464" v-show="num===1&&sysDatas.length>0">
            <div class="mt20 mb20" style="display: flex;flex-direction: row">
                <div class="mt20 mb20" style="text-align: center;width: 25%;font-weight: bold">
                    关联系统:
                </div>
                <div class="mt20 mb20" style="text-align: left;width: 80%">
                    <span class="sysTable" v-for="(item,index) in sysDatas" v-if="index<5">{{item.label}}</span>
                </div>
            </div>
        </div>

		<!--关联接口列表-->
        <div class="need-form" v-show="num===1&&sysDatas.length>0">
            <el-table :data="IFaceDatas">
                <el-table-column label="关联系统" prop="sysName" align="center">
                    <template slot-scope="scope">
                        {{scope.row.sysName}}
                    </template>
                </el-table-column>
                <el-table-column label="系统负责人" prop="sysName" align="center">
                    <template slot-scope="scope">
                        {{scope.row.principal}}
                    </template>
                </el-table-column>
                <el-table-column label="接口名称" prop="jkNameCn" align="center">
                    <template slot-scope="scope">
                        {{scope.row.jkNameCn}}
                    </template>
                </el-table-column>
                <el-table-column label="接口编号" prop="jkNameEn" align="center">
                    <template slot-scope="scope">
                        {{scope.row.jkNameEn}}
                    </template>
                </el-table-column>
            </el-table>
        </div>

		<!--派送-->
        <div class="footer mt20" style="text-align: center;" v-show="num===2">
            <el-button type="primary" style="width: 150px" @click="submitApplication">派送</el-button>
        </div>

		<!--上一步 下一步-->
        <div class="mt20 mb20" style="width: 100%;text-align: center;" v-if="num<2">
            <el-button type="warning" icon="el-icon-step" size="mini" :disabled="next" @click="go()">下一步</el-button>
        </div>




        <!--选择系统-->
        <el-dialog title="关联系统" :visible.sync="open" append-to-body center width="700px">
            <div class="app-container" style="margin: auto">
                <el-transfer
                    @change="changeSystem"
                    :titles="['所有系统', '关联系统']"
                    :filter-method="filterMethod"
                    filterable
                    filter-placeholder="请输入系统名称"
                    v-model="sys"
                    :data="sysList">
                </el-transfer>
                <el-transfer
                    @change="changeIFace"
                    :titles="['接口列表', '关联接口']"
                    filterable
                    filter-placeholder="请输入系统名称"
                    v-model="IFace"
                    :data="IFaceList">
                </el-transfer>

                <div slot="footer" class="dialog-footer mt20" style="text-align: center">
                    <el-button type="primary" @click="submitActForm">确 定</el-button>
                    <el-button @click="cancel">取 消</el-button>
                </div>
            </div>
        </el-dialog>
    </div>
</template>

<script>
import XLSX from "xlsx";
import moment from "moment";
import {getToken} from "@/utils/auth";
import {listSystem} from "@/api/system/list";
import {getListBySysIds} from "@/api/sysinter/sysinter";
import {demandAdd, getDemandInfo, updateDemand,demandAsso} from "@/api/demand/demand";

export default {
    name: "import",
    props:{
		demandId:{
			type:String,
			default:''
		},
		demandState:{
			type:String,
			default:'0'
		},
		taskState:{
			type:String,
			default:'0'
		},

    },
    data(){
        return{
            //录入方式
            radio:1,
            //步骤条参数
            num:0,
			//下一步
			next:true,

            open:false,
			showBreak:false,
            range:[],

            //上传参数
            upload:{
                file:null,
                // 上传的地址
                url:process.env.VUE_APP_BASE_API + "/common/upload",
                // 是否禁用上传
                isUploading: false,
                // 设置上传的请求头部
                headers: {Authorization: "Bearer " + getToken()}
            },
            form:{
                isAutoGet:false,
                demandId:undefined,
                demandName:undefined,
                demandDes:undefined,
				applyTime:this.getNowTime(),
                plantFinishTime:undefined
            },
            rules:{
            },

            //关联系统
            sys:[],
            IFace:[],
            IFaceList:[],

            //被关联列表
            sysDatas:[],
            IFaceDatas:[],

            //全部系统数据
            sysList:[],
        }
    },
    watch: {
        $route(newVal, oldVal) {
            if (newVal.name !== "/demand") {
                this.$destroy();
            } else {
                this.$route.meta.title = '编辑'
            }
        },
    },
    created() {
		const demandId = this.$route.query.demandId
		this.demandState= this.$route.query.demandState
		this.taskState= this.$route.query.taskState

        if (this.$route.query.demandId){
			this.getDemandInfo(demandId);
			this.showBreak = true;
			//重置
			this.resetAssoData();
			this.radio = 1;
			this.num = 1;
			if (this.taskState==='0'&&this.demandState==='1'){
				this.next = false;
			}
        }
        this.getSysList();
    },
    methods:{
		//录入方式选择
		handleChange(value){
			if (value===2){
				this.next = false;
			}
			if (value===1){
				this.next = true;
			}
		},
        //自动生成需求ID
        AutoGenerate(item){
            let that = this
            if (item){
                that.form.demandId = that.GenerateID();
            }else {
                that.form.demandId = undefined;
            }
        },
        //生成需求ID
        GenerateID(){
            var d = new Date();
            var year = d.getFullYear();
            var month = d.getMonth() + 1;
            var date = d.getDate();
            var day = d.getDay();
            var hours = d.getHours();
            var minutes = d.getMinutes();
            var seconds = d.getSeconds();
            var ms = d.getMilliseconds();
            year = (year + "");
            if (month <= 9)
                month = "0" + month;
            if (date <= 9)
                date = "0" + date;
            if (hours <= 9)
                hours = "0" + hours;
            if (minutes <= 9)
                minutes = "0" + minutes;
            if (seconds <= 9)
                seconds = "0" + seconds;
            var id ="XQ-"+ year + month + date + hours + minutes + seconds;
            return id;
        },

        //当前日期
        getNowTime() {
            var now = new Date();
            var year = now.getFullYear(); //得到年份
            var month = now.getMonth(); //得到月份
            var date = now.getDate(); //得到日期
            month = month + 1;
            month = month.toString().padStart(2, "0");
            date = date.toString().padStart(2, "0");
            var defaultDate = `${year}-${month}-${date}`;
            return defaultDate;
        },
        // 文件上传中处理
        handleFileUploadProgress(event, file, fileList) {
            this.upload.isUploading = true;
        },
        // 文件上传成功处理
        handleFileSuccess(response, file, fileList) {
			this.$refs.upload.clearFiles();
            this.upload.isUploading = false;
			//上传完成进入到需求表单界面
			this.previous = false;
			this.num = 1;
			this.next = true;

            this.msgSuccess(response.msg, "导入结果", {dangerouslyUseHTMLString: true});
        },
        //解析表格
        loadFile(file, fileList) {
            this.upload.file = fileList[0].raw;
            const reader = new FileReader();
            const formData = [];
            reader.readAsBinaryString(this.upload.file);
            reader.onload = ev => {
                try {
                    const f = ev.target.result;
                    const workbook = XLSX.read(f, {type: "binary",cellDates: true}, {header:0});
                    formData.push(XLSX.utils.sheet_to_json(workbook.Sheets[workbook.SheetNames[0]],{range:0}))
                    //服务分类
                    const res= formData[0].map((item)=>{
                        return {
                            demandId: item['需求编号'],
                            demandName: item['需求名称'],
                            demandDes: item['需求描述'],
                            plantFinishTime:this.convertExcelDateFormat(item['需求预计完成时间']),
							applyTime:this.getNowTime()
                        }
                    })
                    this.form = res[0]
                    this.range = [this.form.applyTime,this.form.plantFinishTime]
                } catch (e) {
                    console.log(e);
                    return false;
                }
            };
        },
        convertExcelDateFormat: function (date) {//日期转换
            if (date === undefined || date === null || date === "") {
                return null;
            }
            //非时间格式问题  返回Invalid date
            let retFormat = moment(date).format('YYYY-MM-DD');
            if (retFormat === "Invalid date"){
                return retFormat;
            }
            return moment(date).add(1, 'days').format('YYYY-MM-DD')
        },
        //下一步
        go(){
            if (this.num===2){
                return;
            }
            this.num++;
			this.next = true;
        },
        //提交数据
        submit(){
            if (this.range){
                this.form.applyTime = this.range[0]
                this.form.plantFinishTime =  this.range[1]
            }
            demandAdd(this.form).then(res=>{
                if (res.code===200){
					this.radio = 1
                    this.msgSuccess("保存成功!")
                    this.getDemandInfo(this.form.demandId);
					this.showBreak = true;
                }

            })
        },
        getDemandInfo(demandId){
          getDemandInfo(demandId).then(res =>{
			  this.range = [res.data.applyTime,res.data.plantFinishTime]
              this.form = res.data
			  this.IFaceDatas = res.data.list
			   res.data.list.forEach(item=>{
				   this.sysDatas.push(
					   {label:item.sysName}
				   )
			  })
			  this.sysDatas = this.uniqueFunc(this.sysDatas,'label')
          })
        },
		//去重
		uniqueFunc(arr, uniId){
			const res = new Map();
			return arr.filter((item) => !res.has(item[uniId]) && res.set(item[uniId], 1));
		},


		//分解
        breakDown(){
            this.open = true
        },
        //过滤方法
        filterMethod(query, item) {
            return item.label.indexOf(query) > -1;
        },
        //系统列表
        getSysList(){
            listSystem().then(res=>{
                let data = [];
                let allData = res.rows;
                allData.forEach((item,index)=>{
                    data.push({
                        label: item.sysName,
                        sysId:item.sysId,
                        //这里的key值一定要是index，否则目标列表无法显示
                        key: index
                    });
                })
                this.sysList = data
            })
        },
        changeSystem: function (sys) {
            let sysList = []
            for (let i = 0; i < sys.length; i++) {
                this.sysList.forEach((filItem, index) => {
                    if (filItem.key === sys[i]) {
                        sysList.push({
							sysId: filItem.sysId,
                            label:filItem.label
                        });
                    }
                })
            }
            this.sysDatas =  sysList
            let params = {
                sysList:JSON.stringify(sysList)
            }
            //获取需要关联的接口列表
            getListBySysIds(params).then(res => {
                let data = [];
                let allData = res.data;
                allData.forEach((item,index)=>{
                    data.push({
                        label: item.jkNameCn,
                        jkNameEn:item.jkNameEn,
                        jkVersionId:item.jkVersionId,
                        sysId:item.systemId,
                        principal:item.principal,
                        sysName:item.sysName,
                        //这里的key值一定要是index，否则目标列表无法显示
                        key: index
                    });
                })
                this.IFaceList = data
            })
        },
        //关联接口值改变
        changeIFace(IFace){
            let IFaceList = []
            for (let i = 0; i < IFace.length; i++) {
                this.IFaceList.forEach((filItem, index) => {
                    if (filItem.key === IFace[i]) {
                        IFaceList.push({
                            jkVersionId: filItem.jkVersionId,
                            jkNameCn:filItem.label,
                            jkNameEn:filItem.jkNameEn,
                            sysName:filItem.sysName,
                            principal:filItem.principal
                        });
                    }
                })
            }
            this.IFaceDatas =  IFaceList
        },
        cancel(){
            this.open =false;
        },
		//提交关联信息
        submitActForm(){
			this.IFaceDatas.forEach(filItem=>{
				filItem.demandId=this.form.demandId
			})
			let params = {
				IFaceDatas:JSON.stringify(this.IFaceDatas),
				demandId:JSON.stringify(this.form.demandId),
				sysDatas:JSON.stringify(this.sysDatas)
			}
			demandAsso(params).then(response=>{
				console.log(response)
			}).then(()=>{
				this.open =false;
				this.demandState = 1;
				this.next = false;
			})

        },
        //派送
        submitApplication(){
			var that = this
			that.form.taskState = 1;
			that.$confirm('是否确认向系统负责人派送需求信息?', "警告", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
            }).then(function () {
				that.form.demandState = '1';
				that.form.taskState = '1';
				return updateDemand(that.form);
            }).then(() => {
				that.$emit('getList');
				that.msgSuccess("派送成功");
				that.$store.dispatch('tagsView/delView', that.$route).then(() => {
					that.$router.push({
						path: '/need/list'
					});
				});
			}).catch(function (e) {
				if (e === 'cancel'){
					that.msgWarning("已取消");
					return;
				}
				that.msgError("派送失败:"+e);
            });
        },
		//重置关联系统接口数据
		resetAssoData(){
			this.IFaceDatas = [];
			this.sysDatas = [];
		}
    }
}
</script>

<style lang="scss" scoped>
/*添加CSS类*/
    .layui-transfer-header {
        display: table-cell;
        vertical-align: middle;
        width: inherit;
    }

    .el-dropdown-link {
        cursor: pointer;
        color: #409EFF;
    }
    .el-icon-arrow-down {
        font-size: 12px;
    }
    .el-divider__text{
        color: #a59898;
    }
    .need-form{
        margin-bottom: 20px;
        border: 3px solid #bcb1b1;
        border-radius: 3px;
        padding: 10px;
    }
    .sysTable{
        border: #bcb1b1 solid 1px;padding: 10px;margin: 10px;border-radius: 5px
    }
    .sysTable:hover{
        cursor: pointer;
        color: #1c84c6;
    }
</style>
