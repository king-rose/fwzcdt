<template>
    <div class="app-container">
        <el-card class="mb20">
            <template slot="header">
                <span style="font-size: 20px;font-weight: 550;color: #1f2d3d">服务信息</span>
            </template>

            <div slot="default">
                <el-row :gutter="12" class="el-row">
                    <el-col class="title" :span="6"><span>服务大类:</span></el-col>
                    <el-col class="content" :span="6"> <span>{{service.serviceHeadClass}}</span></el-col>
                    <el-col class="title" :span="6"><span>服务子类:</span></el-col>
                    <el-col class="content" :span="6"> <span>{{service.serviceSmallClass}}</span></el-col>
                </el-row>

                <el-row :gutter="12" class="el-row">
                    <el-col class="title" :span="6"><span>服务编号:</span></el-col>
                    <el-col class="content" :span="6"> <span>{{service.svcCode}}</span></el-col>
                    <el-col class="title" :span="6"><span>服务名称:</span></el-col>
                    <el-col class="content" :span="6"> <span>{{service.svcName}}</span></el-col>
                </el-row>

                <el-row :gutter="12" class="el-row">
                    <el-col class="title" :span="6"><span>服务描述:</span></el-col>
                    <el-col class="content" :span="6"> <span>{{service.svcDes}}</span></el-col>
                </el-row>
            </div>
        </el-card>

		<el-table
			ref="fileTable"
			v-loading="loading"
			highlight-current-row
			@row-click="handleClickTableRow"
			:cell-style="cellStyle"
			:header-cell-style="{'background-color': '#eef1f6','color': 'rgb(96,98,102)','font-size':'18','border-bottom': '1px rgb(103, 194, 58) solid'}"
			:data="sceneList">
			<el-table-column type="selection" min-width="3%" align="center"></el-table-column>
			<el-table-column prop="svcCode" label="服务编号" min-width="10%" align="center"></el-table-column>
			<el-table-column prop="svName" label="服务名称" min-width="10%" align="center"></el-table-column>
			<el-table-column prop="svCode" label="场景码" min-width="5%" align="center"></el-table-column>
			<el-table-column prop="svName" label="场景中文名称" min-width="10%" align="center"></el-table-column>
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
		</el-table>
		<pagination
			v-show="total>0"
			:total="total"
			:page.sync="queryParams.pageNum"
			:limit.sync="queryParams.pageSize"
			@pagination="getList"
		/>
    </div>
</template>

<script>
	import {getServiceInfo} from "@/api/service/oprservice";
	import {sceneList} from '@/api/service/oprscenario'

	export default {
		name: "serviceDetail",
		data() {
			return {
				service:{},
				serviceId:'',
				loading:false,
				sceneList: [],
				total:0,
				queryParams:{
					pageNum:1,
					pageSize:10,
					serviceId:''
				}
			};
		},
		activated() {
			this.serviceId = this.$route.query.svcId;
			this.getSceneData();
		},
		created() {
			this.getDicts("aesb_audit_state").then(response => {
				this.auditStateOptions = response.data;
			});
		},
		methods: {
			getSceneData(){
				this.loading = true;
				getServiceInfo(this.serviceId).then(res=>{
					this.service = res.data
				})
				this.queryParams.serviceId = this.serviceId;
				sceneList(this.queryParams).then(res => {
					this.sceneList = res.rows;
					this.total = res.total;
					this.loading = false
				})
			},
			handleClickTableRow(row, column, event) {
				if (column.label === '场景中文名称' || column.label === '操作') {
					return;
				}
				this.$refs.fileTable.toggleRowSelection(row);
			},
			// 审核状态字典翻译
			stateFormat(row, column) {
				return this.selectDictLabel(this.auditStateOptions, row.auditState);
			},
			//单元格样式
			cellStyle(row , colum , rowIndex , columnIndex) {
				if (row.row.auditState == "2" && row.column.label == '审核状态') {
					return "color : #13ce66;font-weight:500";
				}
				if (row.row.auditState == "0" && row.column.label == '审核状态') {
					return "color : #ffba00;font-weight:500";
				}
				if (row.row.auditState == "1" && row.column.label == '审核状态') {
					return "color : #1890ff;font-weight:500";
				}
				if (row.row.auditState == "3" && row.column.label == '审核状态') {
					return "color : #ff2c50;font-weight:500";
				}
			}
		}
	};
</script>

<style lang="scss" scoped>
	.el-row{
		border-radius: 15px;
		background-color: #44464c;
		margin-top: 25px;
		border: solid #EEF1F6 1px;
		padding: 10px
	}
	.title{
		color: #fafbff;
		text-align: center;
		font-size: 16px
	}
	.content{
		color: #fafbff;
		text-align: left;
		font-size: 14px;
		line-height: 24px
	}
	.content:hover{
		color: #075eee;
	}
</style>
