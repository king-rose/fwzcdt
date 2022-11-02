<template>
    <div>
        <div class="Fast" id="fast"></div>
    </div>

</template>

<script>
    import echarts from 'echarts'
	import {serviceScene} from '@/api/system/list'

    export default {
        components: {
            echarts
        },
		props:{
			open:{
				type:Boolean,
				default:false
			},
			proSysId:{
				type:String,
				default:''
			}
		},
        data() {
            return {
            }
        },
        mounted() {
            this.chartInit();
        },
        methods: {
            chartInit() {
				serviceScene().then(res=>{
                    let data = res.data
                    const that = this
                    var chartDom = document.getElementById('fast');
                    var myChart = echarts.init(chartDom);
                    var option;
                    var color = ['#0cd2e6', '#91cc75', '#fac858', '#ee6666', '#73c0de', '#3ba272', '#fc8452','#aee498'];

                    var title =['系统代码','提供方系统','负责人','场景消费数'] ;
                    var legend= data.map(item=>{
                        return item.sysName;
                    })
                    var seriesData = []
                    seriesData = res.data.map(item=>{
                        return Object.assign({}, { name: item.sysName, value: item.sysNum,code:item.sysCode,principal:item.principal,sysId:item.sysId})
                    })
                    option = {
                        backgroundColor:'#fff',
                        color: color,
                        title: [
                            {
                                top: '3%',
                                right: '47%',
                                text: title[0],
                                textStyle: {
                                    fontSize: 11,
                                    color: '#767272',
                                },
                            },
                            {
                                top: '3%',
                                right: '31%',
                                text: title[1],
                                textStyle: {
                                    fontSize: 11,
                                    color: '#767272',
                                },
                            },
                            {
                                top: '3%',
                                right: '19%',
                                text: title[2],
                                textStyle: {
                                    fontSize: 11,
                                    color: '#767272',
                                },
                            },
                            {
                                top: '3%',
                                right: '5%',
                                text: title[3],
                                textStyle: {
                                    fontSize: 11,
                                    color: '#767272',
                                },
                            }
                        ],

                        tooltip: {
                            trigger: 'item',
                            formatter:"{a} {b}：{c}",
                        },
                        legend: {
                            orient: 'vertical',
                            type: 'scroll',
                            top: 'center',
                            itemGap: 10,
                            itemWidth:20,
                            itemHeight:10,
                            left: '40%',
                            itemStyle: {
                                borderColor: 'inherit',
                                borderWidth: 1,
                            },
                            textStyle: {
                                color:'inherit',
                                rich: {
                                    name: {
                                        color: 'rgba(0, 0, 0, 0.65)',
                                        width: 100,
                                        fontSize: 12,
                                        padding: 2
                                    },
                                    value: {
                                        color: 'rgba(0, 0, 0, 0.65)',
                                        width: 55,
                                        fontSize: 12,
                                        padding: 2
                                    },
                                    code: {
                                        color: 'rgba(0, 0, 0, 0.65)',
                                        width: 75,
                                        fontSize: 12,
                                        padding: 2
                                    },
									principal:{
                                        color: 'rgba(0, 0, 0, 0.65)',
                                        width: 80,
                                        fontSize: 12,
                                        padding: 2
                                    }
                                },
                            },
                            style:{
                                textAlign:'center'
                            },
                            data: legend,
							formatter: function(name){
								if (seriesData.length){
									const item = seriesData.filter((item) => item.name === name)[0];
									var str = ``;
									str+=`{code| ${item.code}}`;
									if (item.name.length>6){
										str+=`{name| ${item.name.slice(0,6)}...}`;
									}else {
										str+=`{name| ${item.name}}`;
									}
									str+=`{principal| ${item.principal}}`;
									str+=`{value| ${item.value}}`;
									return str;
								}
							}
                        },
                        series: [{
                            name: '',
                            type: 'pie',
                            center: ['20%', '50%'],
                            radius: ['45%', '65%'],
                            emphasis: {
                                focus: 'series'
                            },
                            itemStyle: {
                                normal: {
                                    borderWidth:5,
                                    borderColor: "#fff"
                                }
                            },

                            label: {
                                normal: {
                                    show: false,
                                    position: 'center',
                                    formatter: '{value|{c}}\n{label|{b}}',
                                    rich: {
                                        value: {
                                            padding: 5,
                                            align: 'center',
                                            verticalAlign: 'middle',
                                            fontSize: 32,
                                        },
                                        label: {
                                            align: 'center',
                                            verticalAlign: 'middle',
                                            fontSize: 16,
                                        },
                                    },
                                },
                                emphasis: {
                                    show: true,
                                    textStyle: {
                                        fontSize: '12',
                                    },
                                },
                            },
                            labelLine: {
                                show: false,
                                length: 0,
                                length2: 0,
                            },
                            data: seriesData,
                        }],
                    };

                    option && myChart.setOption(option);
					myChart.on('click', function (params) {
						//params.data.sysId
						// 在子组件中触发在父组件中被绑定的方法
						that.$emit('update:proSysId',params.data.sysId);
						that.$emit('update:open', true);

					});

					myChart.on('restore',function () {
						option && myChart.setOption(option);
					})
                })
            },

        }
    }
</script>
<style scoped>
    .Fast {
        width: 100%;
        height: auto;
        display: flex;
        justify-content: space-between;
        flex-wrap: wrap;
    }

    #fast {
        width: 630px;
        height: 325px;
    }

    .Fast-list-bottom {
        font-size: 12px;
        color: #3F495E;
        text-align: center;
    }

    .icon-ship {
        color: #18FD6C;
    }

    .icon-refund {
        color: #F85E1F;
    }

    .icon-feedback {
        font-weight: bolder;
        color: #FE4B60;
    }

    .icon-shelves {
        color: #18A2FD;
    }

    .icon-finance {
        color: #09F8BA;
    }

    .icon-analysis {
        color: #952BFD;
    }
</style>
