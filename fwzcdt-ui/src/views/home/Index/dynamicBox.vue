<template>
	<div id="dynamic" class="dynamic">

	</div>
</template>

<script>
import echarts from 'echarts';
import {getChangeTrend} from "@/api/system/list"
import item from '@/layout/components/Sidebar/Item'

export default {
	components: {
		echarts
	},
	data() {
		return {
			xData:[],
			yAdata:[],
			yBdata:[],
			yCdata:[]
		}
	},
	props: {
		date: null
	},
	mounted() {
		this.chartInit(this.date);
	},
	methods: {
		chartInit(date) {
			this.getChangeTrendInfo(date);
		},
		getChangeTrendInfo(date){
			var chartDom = document.getElementById('dynamic');
			var myChart = echarts.init(chartDom);
			myChart.showLoading({
				text: '请稍后......',
				color: '#327EF3',
				textColor: '#327EF3'
			})
			//获取数据
			getChangeTrend(date).then(res => {
				var xName;//单位
				var xData =[],yAdata=[],yBdata=[],yCdata=[]

				yAdata = res.data.map(item =>{
					return item.doneNum;
				});
				yBdata = res.data.map(item =>{
					return item.dealNum;
				});
				yCdata = res.data.map(item =>{
					return item.faceNum;
				});
				if (date===0){
					xName = "年份"
					xData = res.data.map(item =>{
						return item.dealYear;
					});

				}
				if (date===1){
					xName = "季度"
					xData = res.data.map(item =>{
						return item.dealQuarter;
					});
				}
				if (date===2){
					xName = "月份"
					xData = res.data.map(item =>{
						return item.dealMonth;
					});
				}

				var option;
				option = {
					tooltip: {
						trigger: 'item',
						axisPointer: { // 坐标轴指示器，坐标轴触发有效
							type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
						}
					},
					grid: {
						left: '2%',
						right: '4%',
						bottom: '14%',
						top: '16%',
						containLabel: true
					},
					legend: {
						data: ['已消费', '待消费', '接口数'],
						left: '7%',
						top: '5%',
						textStyle: {
							color: "#666666"
						},
						itemWidth: 15,
						itemHeight: 10,
						itemGap: 25
					},
					xAxis: {
						type: 'category',
						name: xName,// 给X轴加单位
						nameTextStyle: {
							color: 'black',
							padding: [0, 0, -50, 0]
						},
						data: xData,
						axisLine: {
							lineStyle: {
								color: '#cdd5e2'

							}
						},
						axisLabel: {
							interval: 0,
							textStyle: {
								color: "#666666"
							}
						},
					},

					yAxis: [{
						type: 'log',
						min: 1,
						logBase: 10,
						axisLine: {
							show: false,
							lineStyle: {
								color: '#cdd5e2'
							}
						},
						splitLine: {
							show: false,
						},
						axisLabel: {
							textStyle: {
								color: "#666666"
							},
						},
						axisLine: {
							lineStyle: {
								color: '#cdd5e2'
							}
						},
					},
						{
							type: "value",
							name: "接口数",
							nameTextStyle: {
								color: "#666666"
							},
							position: "right",
							axisLine: {
								lineStyle: {
									color: '#cdd5e2'
								}
							},
							splitLine: {
								show: false,
							},
							axisLabel: {
								show: true,
								formatter: "{value} 个", //右侧Y轴文字显示
								textStyle: {
									color: "#666666"
								}
							}
						}
					],
					series: [{
						name: '已消费',
						type: 'bar',
						barWidth: '12px',
						itemStyle: {
							normal: {
								color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
									offset: 0,
									color: '#29acff'
								}, {
									offset: 1,
									color: '#4bdfff'
								}]),
								barBorderRadius: 6,
							},
						},
						data: yAdata
					},
						{
							name: '待消费',
							type: 'bar',
							barWidth: '12px',
							itemStyle: {
								normal: {
									color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
										offset: 0,
										color: '#01c871'
									}, {
										offset: 1,
										color: '#55f49c'
									}]),
									barBorderRadius: 6,
								}

							},
							data: yBdata
						},
						{
							name: "接口数",
							type: "line",
							yAxisIndex: 1, //使用的 y 轴的 index，在单个图表实例中存在多个 y轴的时候有用
							smooth: false, //平滑曲线显示

							symbol: "circle", //标记的图形为实心圆
							symbolSize: 8, //标记的大小
							itemStyle: {
								normal: {
									color: '#ffa43a',
									borderColor: 'rgba(255, 234, 0, 0.5)',  //圆点透明 边框
									borderWidth: 5
								},

							},
							lineStyle: {
								color: "#ffa43a"
							},

							data: yCdata
						}
					]
				};
				option && myChart.setOption(option);
				myChart.hideLoading();
			})
		}

	}
}
</script>
<style scoped>
.dynamic {
	width: 100%;
	height: auto;
	display: flex;
	justify-content: space-between;
	flex-wrap: wrap;
}

#dynamic {
	width: 915px;
	height: 538px;
}


</style>
