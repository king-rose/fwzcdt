<template>
	<div class="app-container">
		<div class="header">
			<el-form v-show="showSearch" ref="queryForm" :inline="true" :model="queryParams" label-width="108px"
					 size="mini">
				<el-form-item label="需求编号:" prop="demandId">
					<el-input v-model="queryParams.demandId" placeholder="需求编号.."/>
				</el-form-item>
				<el-form-item label="需求名称:" prop="demandName">
					<el-input v-model="queryParams.demandName" placeholder="请输入需求名称.."/>
				</el-form-item>
				<el-form-item label="申请人名称:" prop="applyUserName">
					<el-input v-model="queryParams.applyUserName" placeholder="申请人名称.."/>
				</el-form-item>
				<el-form-item label="任务单状态:" prop="taskState">
					<el-select v-model="queryParams.taskState">
						<el-option
							v-for="dict in taskStateOptions"
							:key="dict.dictValue"
							:label="dict.dictLabel"
							:value="dict.dictValue"
						/>
					</el-select>
				</el-form-item>
				<el-form-item>
					<el-button icon="el-icon-search" size="mini" type="cyan" @click="handleQuery">搜索</el-button>
					<el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
				</el-form-item>

			</el-form>
			<right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
		</div>

		<div class="table-fixed">
			<!--表格-->
			<el-table v-loading="loading" :data="taskList" @selection-change="handleSelectionChange">
				<el-table-column align="center" label="需求编号" prop="demandId"/>
				<el-table-column align="center" label="需求名称" prop="demandName"/>
				<el-table-column :formatter="statusFormat" align="center" label="需求状态" prop="demandState">
					<template slot-scope="scope">
						<span
							:class="scope.row.demandState==='0'? 'demandClass':scope.row.demandState==='1'? 'demandClass_1':'demandClass_2'"
							:title="statusFormat(scope.row,scope.column)">
							{{ statusFormat(scope.row, scope.column) }}
						</span>
					</template>
				</el-table-column>
				<el-table-column align="center" label="任务状态" prop="taskState">
					<template slot-scope="scope">
						<span
							:class="scope.row.taskState==='1'? 'demandClass':scope.row.taskState==='2'? 'demandClass_1':scope.row.taskState==='3'?'demandClass_2':'demandClass_3'">
							{{scope.row.taskState === '0'?'未受理': scope.row.taskState === '1' ? '已派送' : scope.row.taskState === '2' ? '已受理' : scope.row.taskState === '3' ? '已通过':'已驳回' }}</span>
					</template>
				</el-table-column>
				<el-table-column align="center" label="需求申请日期" prop="applyTime"/>
				<el-table-column align="center" label="需求申请人" prop="applyUserName"/>
				<el-table-column align="center" label="操作" prop="percentage">
					<template slot-scope="scope">
						<el-button @click="clickAccept(scope.row)" type="text" size="mini" :icon="scope.row.taskState==='2'? 'el-icon-check':scope.row.taskState==='3'?'el-icon-finished':'el-icon-circle-close'">
							{{scope.row.taskState==='2'? '审批':scope.row.taskState==='3'?'已审批':'已驳回'}}
						</el-button>
					</template>
				</el-table-column>
			</el-table>
			<pagination
				v-show="total>0"
				:limit.sync="queryParams.pageSize"
				:page.sync="queryParams.pageNum"
				:total="total"
				@pagination="getList"
			/>
		</div>

		<el-dialog :visible.sync="open" append-to-body destroy-on-close title="需求审批">
			<el-card class="box-card">
				<div slot="header" class="clearfix">
					<span class="text-muted">需求信息</span>
				</div>
				<el-form>
					<el-row :gutter="5">
						<el-col :span="12">
							<el-form-item label="需求编号:">
								<el-input v-model="form.demandId" disabled/>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="需求名称:">
								<el-input v-model="form.demandName" disabled/>
							</el-form-item>
						</el-col>
					</el-row>
					<el-row :gutter="10">
						<el-col :span="12">
							<el-form-item label="需求描述:">
								<el-input v-model="form.demandDes" disabled type="textarea"/>
							</el-form-item>
						</el-col>
					</el-row>
				</el-form>
			</el-card>

			<el-card class="box-card">
				<div slot="header" class="clearfix">
					<span class="text-muted">关联接口</span>
				</div>
				<div>
					<el-table v-loading="loading_1" :data="faceList">
						<el-table-column label="关联系统" prop="sysName"></el-table-column>
						<el-table-column label="接口编号" prop="jkNameEn"></el-table-column>
						<el-table-column label="接口名称" prop="jkNameCn"></el-table-column>
						<el-table-column label="工作用时(天)" prop="workTime">
							<template slot-scope="scope">
								<el-input-number v-model="scope.row.workDay" :min="1" style="width: 120px"></el-input-number>
							</template>
						</el-table-column>
						<el-table-column label="系统负责人" prop="principal"></el-table-column>
					</el-table>
				</div>
			</el-card>

			<el-card class="box-card">
				<div slot="header" class="clearfix">
					<span class="text-muted">审批信息</span>
				</div>
				<div>
					<el-form>
						<el-row :gutter="10">
							<el-col :span="12">
								<el-form-item label="审批结果:">
									<el-radio-group v-model="approval.isAccept">
										<el-radio label="1">同意</el-radio>
										<el-radio label="0">驳回</el-radio>
									</el-radio-group>
								</el-form-item>
							</el-col>
						</el-row>
						<el-row :gutter="10">
							<el-col :span="12">
								<el-form-item label="备注信息:" v-if="approval.isAccept==='0'">
									<el-input v-model="approval.acceptContent" type="textarea"/>
								</el-form-item>
							</el-col>
						</el-row>
					</el-form>
				</div>
			</el-card>

			<div slot="footer" class="dialog-footer" style="text-align: right">
				<el-button :disabled="taskState==='3' || taskState==='4'" type="primary" @click="submit">{{taskState==='2'? '确认':taskState==='3'?'已审批':'已驳回'}}</el-button>
				<el-button @click="cancel">取 消</el-button>
			</div>

		</el-dialog>
	</div>
</template>

<script>
import {listDemandAcceptList, getDemandInfo, updateDemand,DemandFaceDown} from "@/api/demand/demand";
import * as echarts from 'echarts';

export default {
	name: "taskAccept",
	data() {
		return {
			demandId: '',
			taskState: '',
			workTime: 1,
			//遮罩层
			open: false,

			//查询参数
			queryParams: {
				pageNum: 1,
				pageSize: 10,
				demandId:undefined,
				demandName:undefined,
				applyUserName:'',
				taskState: undefined,
			},

			//表单参数
			form: {},
			approval:{
				isAccept:'1',
				acceptContent:undefined
			},
			//任务状态字典
			stateOptions: [],
			//显示搜索条件
			showSearch: true,
			//遮罩层
			loading: false,
			loading_1: false,
			//表格数据
			taskList: [],
			faceList: [],
			total: 0,
			//需求状态
			demandStateOptions: [],
			//任务单状态
			taskStateOptions: [],

			// 选中数组
			ids: [],
			// 非单个禁用
			single: true,
			// 非多个禁用
			multiple: true,
		}
	},
	created() {
		this.getList();
		this.getDicts("aesb_demand_state").then(response => {
			this.demandStateOptions = response.data;
		});
		this.getDicts("aesb_task_state").then(response => {
			this.taskStateOptions = response.data;
		});

	},
	methods: {
		//任务单列表
		getList() {
			this.loading = true;
			listDemandAcceptList(this.queryParams).then(response => {
				this.taskList = response.rows;
				this.total = response.total;
			})
			this.loading = false;
		},
		// 字典状态字典翻译
		statusFormat(row, column) {
			return this.selectDictLabel(this.demandStateOptions, row.demandState);
		},
		// 多选框选中数据
		handleSelectionChange(selection) {
			this.ids = selection.map(item => item.id)
			this.single = selection.length !== 1
			this.multiple = !selection.length
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
		//审批按钮
		clickAccept(row){
			this.demandId = row.demandId;
			this.taskState  = row.taskState;
			this.open = true;
			this.getDetail(row.demandId)
		},
		getDetail(demandId) {
			getDemandInfo(demandId).then(response => {
				var that = this;
				that.faceList = response.data.list
				that.form = response.data
				that.approval.isAccept = response.data.isAccept
				that.approval.acceptContent = response.data.acceptContent
				that.loading_1 = false;
			})
		},
		//提交
		submit() {
			let params = {
				demandId: this.demandId,
				acceptTime:new Date(),
				acceptUser:this.$store.getters.name,
				isAccept:this.approval.isAccept,
				acceptContent:this.approval.acceptContent,
				demandState:"",
				taskState: ""
			}
			if (this.approval.isAccept==='1'){//同意
				params.demandState = "2";
				params.taskState = "3";

			}
			if (this.approval.isAccept==='0'){//驳回
				let params_1 = {
					demandId: this.demandId,
					isAccept: "0"
				}
				//修改接口受理状态
				DemandFaceDown(params_1).then(response=>{
					console.log(response)
				})

				params.demandState = "1";
				params.taskState = "4";
			}
			console.log(params)
			updateDemand(params).then(res => {
				this.open = false;
				this.msgSuccess("审批完成!")
				this.getList();
			})
		},
		//取消
		cancel() {
			this.open = false;
		},
	}
}
</script>

<style scoped>
.box-card{
	margin-top: 15px;
}
.clearfix{
	font-size: 15px;
	font-weight: bold;
}
.demandClass_1 {
	color: #1c84c6;
}

.demandClass_2 {
	color: #1ab394;
}

.demandClass_3{
	color: #ff4949;
}
</style>
