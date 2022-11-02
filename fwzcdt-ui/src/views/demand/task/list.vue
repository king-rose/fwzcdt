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
			<right-tool-chart-bar :showSearch.sync="showSearch" @queryTable="getList" :showChart.sync="showChart"></right-tool-chart-bar>

		</div>

		<div>
			<!--视图-->
			<div style="margin-top: 40px;text-align: center;width: 100%">
				<el-collapse-transition>
					<div id="chart" v-show="!showChart"></div>
				</el-collapse-transition>
			</div>
			<!--表格-->
			<el-table v-loading="loading" :data="taskList" @selection-change="handleSelectionChange">
				<el-table-column align="center" label="需求编号" prop="demandId"/>
				<el-table-column align="center" label="需求名称" prop="demandName"/>
				<el-table-column :formatter="statusFormat" align="center" label="需求状态" prop="demandState">
					<template slot-scope="scope">
						<span
							:class="scope.row.demandState==='0'? 'demandClass':scope.row.demandState==='1'? 'demandClass_1':'demandClass_2'"
							:title="statusFormat(scope.row,scope.column)"
							@click="acceptTask(scope.row)">{{ statusFormat(scope.row, scope.column) }}</span>
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
				<!--				<el-table-column align="center" label="任务开发进度" prop="percentage">
									<template slot-scope="scope">
										<el-progress :color="customColors" :percentage="percentage"></el-progress>
									</template>
								</el-table-column>-->
			</el-table>
			<pagination
				v-show="total>0"
				:limit.sync="queryParams.pageSize"
				:page.sync="queryParams.pageNum"
				:total="total"
				@pagination="getList"
			/>
		</div>

		<el-dialog :visible.sync="open" append-to-body destroy-on-close title="任务单受理信息">
			<el-form>
				<el-row :gutter="10">
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
				<el-table-column label="操作">
					<template slot-scope="scope">
						<el-button
							:disabled="scope.row.isAccept==='1'"
							:icon="scope.row.isAccept==='1'?'el-icon-finished':'el-icon-bottom-right'"
							size="mini"
							type="text"
							@click="acceptInterFace(scope.row)"
						>
							{{ scope.row.isAccept === '1' ? '已受理' : '受理' }}
						</el-button>
					</template>
				</el-table-column>
			</el-table>

			<div v-show="!btn" slot="footer" class="dialog-footer mt20" style="text-align: right">
				<el-button :disabled="taskState!=='1'" type="primary" @click="submit">
					{{ taskState === '1' ? '提 交' : '已提交' }}
				</el-button>
				<el-button @click="cancel">取 消</el-button>
			</div>
		</el-dialog>
	</div>
</template>

<script>
import {listDemandTaskList, getDemandInfo, updateDemandFace, updateDemand,getProcess} from "@/api/demand/demand";
import * as echarts from 'echarts';

export default {
	name: "taskList",
	data() {
		return {
			//echarts数据
			chart:{
				demandName:[],

				noAccepted:[],//派送未受理
				noAccepted_rest:[],

				accepted:[],//派送已受理
				accepted_rest:[],

				accepting:[],//审批
				accepting_test:[],

				developing:[],//开发中
				developing_rest:[]
			},



			//显示echarts图
			showChart: false,
			btn: true,
			demandId: '',
			taskState: '',
			workTime: 1,
			//遮罩层
			open: false,

			//查询参数
			queryParams: {
				pageNum: 1,
				pageSize: 10,
				taskState: undefined,
				demandId: undefined,
				demandName: undefined,
			},

			//表单参数
			form: {},
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

			percentage: 0,
			// 选中数组
			ids: [],
			// 非单个禁用
			single: true,
			// 非多个禁用
			multiple: true,
		}
	},
	mounted() {
		this.initChart();
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
		initChart(){
			this.chart.noAccepted=[]
			this.chart.noAccepted_rest=[]
			this.chart.accepted=[]
			this.chart.accepted_rest=[]
			this.chart.accepting=[]
			this.chart.accepting_test=[]
			this.chart.developing=[]
			this.chart.developing_rest=[]
			this.chart.demandName=[]
			getProcess(this.queryParams).then(res=>{
				res.data.forEach(item=>{
					if (item.pswsl){
						this.chart.noAccepted.push(item.pswsl);
						this.chart.noAccepted_rest.push(100-item.pswsl)
					}else {
						this.chart.noAccepted.push(0);
						this.chart.noAccepted_rest.push(100)
					}
					if (item.psysl){
						this.chart.accepted.push(item.psysl);
						this.chart.accepted_rest.push(100-item.psysl)
					}else {
						this.chart.accepted.push(0);
						this.chart.accepted_rest.push(100)
					}
					if (item.psz){
						this.chart.accepting.push(item.psz);
						this.chart.accepting_test.push(100-item.psz)
					}else {
						this.chart.accepting.push(0);
						this.chart.accepting_test.push(100)
					}
					if (item.kfz){
						this.chart.developing.push(item.kfz);
						this.chart.developing_rest.push(100-item.kfz)
					}else {
						this.chart.developing.push(0);
						this.chart.developing_rest.push(100)
					}
					this.chart.demandName.push(item.xqmc);
				})

				var chartDom = document.getElementById('chart');
				var myChart = echarts.init(chartDom);
				var xAxisLabels = [
					{ Text: '派送未受理'},
					{ Text: '派送已受理'},
					{ Text: '审批中' },
					{ Text: '开发中' },
				];

				var option = {
					title:{
						show:true,   //是否显示
						text:"任务进度",
						textStyle:{
							color:"#fff"
						}

					},
					backgroundColor: '#44464c',
					grid: { left: '5%', right: '5%', bottom: '10%', top: '5%', containLabel: true },
					xAxis: {
						type: 'value',
						splitLine: { lineStyle: { color: 'rgba(255,255,255,0.3)' } },
						splitNumber: 4,
						axisTick: { show: false },
						axisLine: { show: false },
						axisLabel: {
							color: '#fff',
							align: 'left',
							fontSize: 20,
							formatter: function (value, index) {
								return xAxisLabels[index] ? xAxisLabels[index].Text : '';
							},
						},
					},
					yAxis: {
						type: 'category',
						axisLine: { lineStyle: { color: 'rgba(255,255,255,0.3)' } },
						splitLine: { show: false },
						axisTick: { show: false },
						axisLabel: { color: 'turquoise', fontSize: 20 },
						data: this.chart.demandName,
					},
					series: [
						{
							name: '1',
							type: 'bar',
							stack: 'processBar',
							barWidth: 34,
							label: {
								show: true,
								fontSize: 20,
								textBorderColor: '#000',
								textBorderWidth: 2,
								formatter: function (params) {
									const value = params.data;
									return value === 0 ? '' : value + '%';
								},
							},
							itemStyle: {
								color: function (params) {
									if (params.data < 30) {
										return "#f47f7e";
									} else if (params.data < 90 && params.data >= 30) {
										return "#ffba00";
									} else {
										return "#10df6a";
									}
								},
							},
							data: this.chart.noAccepted,
						},
						{
							name: '1_REST',
							type: 'bar',
							stack: 'processBar',
							itemStyle: { color: 'rgb(186,179,189,0.1)' },
							data:this.chart.noAccepted_rest,
						},
						{
							name: '2',
							type: 'bar',
							stack: 'processBar',
							label: {
								show: true,
								fontSize: 20,
								textBorderColor: '#000',
								textBorderWidth: 2,
								formatter: function (params) {
									const value = params.data;
									return value === 0 ? '' : value + '%';
								},
							},
							itemStyle: {
								color: function (params) {
									if (params.data < 30) {
										return "#f47f7e";
									} else if (params.data < 90 && params.data >= 30) {
										return "#ffba00";
									} else {
										return "#10df6a";
									}
								},
							},
							data: this.chart.accepted,
						},
						{
							name: '2_REST',
							type: 'bar',
							stack: 'processBar',
							itemStyle: { color: 'rgb(186,179,189,0.1)' },
							data: this.chart.accepted_rest,
						},
						{
							name: '3',
							type: 'bar',
							stack: 'processBar',
							label: {
								show: true,
								fontSize: 20,
								textBorderColor: '#000',
								textBorderWidth: 2,
								formatter: function (params) {
									const value = params.data;
									return value === 0 ? '' : value + '%';
								},
							},
							itemStyle: {
								color: function (params) {
									if (params.data < 30) {
										return "#f47f7e";
									} else if (params.data < 90 && params.data >= 30) {
										return "#ffba00";
									} else {
										return "#10df6a";
									}
								},
							},
							data: this.chart.accepting,
						},
						{
							name: '3_REST',
							type: 'bar',
							stack: 'processBar',
							itemStyle: { color: 'rgb(186,179,189,0.1)' },
							data: this.chart.accepting_test,
						},
						{
							name: '4',
							type: 'bar',
							stack: 'processBar',
							label: {
								show: true,
								fontSize: 20,
								textBorderColor: '#000',
								textBorderWidth: 2,
								formatter: function (params) {
									const value = params.data;
									return value === 0 ? '' : value + '%';
								},
							},
							itemStyle: {
								color: function (params) {
									if (params.data < 30) {
										return "#f47f7e";
									} else if (params.data < 90 && params.data >= 30) {
										return "#ffba00";
									} else {
										return "#10df6a";
									}
								},

							},
							data: this.chart.developing,
						},
						{
							name: '4_REST',
							type: 'bar',
							stack: 'processBar',
							itemStyle: { color: 'rgb(186,179,189,0.1)' },
							data: this.chart.developing_rest,
						},
					],
				};

				option && myChart.setOption(option);
			})
		},
		//任务单列表
		getList() {
			this.loading = true;
			listDemandTaskList(this.queryParams).then(response => {
				this.taskList = response.list;
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
		//受理任务
		acceptTask(row) {
			this.open = true;
			this.loading_1 = true;
			this.demandId = row.demandId;
			this.taskState = row.taskState;
			this.getDetail(this.demandId);
		},
		getDetail(demandId) {
			getDemandInfo(demandId).then(response => {
				var that = this;
				that.faceList = response.data.list
				that.faceList.forEach(item => {
					if (item.isAccept === '0') {
						that.btn = true;
					} else {
						that.btn = false;
					}
				})
				that.form = response.data
				that.loading_1 = false;
			})
		},
		//提交
		submit() {
			let params = {
				demandId: this.demandId,
				taskState: "2"
			}
			updateDemand(params).then(res => {
				this.open = false;
				this.msgSuccess("操作成功")
				this.getList();
			})
		},
		//取消
		cancel() {
			this.open = false;
		},
		//受理接口
		acceptInterFace(row) {
			let params = {
				demandId: row.demandId,
				isAccept: "1",
				acceptTime:new Date(),
				jkVersionId: row.jkVersionId,
				workDay: row.workDay
			}
			updateDemandFace(params).then(response => {
				if (response.code === 200) {
					this.getDetail(row.demandId);
				}
			})
		},
	}
}
</script>

<style scoped>
.demandClass{
	color: #e69a0a;
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
#chart {
	width: 100%;
	height: 450px;
}
</style>
