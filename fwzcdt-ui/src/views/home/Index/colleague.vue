<template>
    <div class="colleague" id="colleague">

    </div>
</template>

<script>
    import echarts from 'echarts'

    import {sceneRatioChart} from  "@/api/system/list"

    export default {
        components: {
            echarts
        },
        data() {
            return{

            }
        },
        mounted() {
            this.chartInit();
        },
        methods:{
            chartInit(){
                sceneRatioChart().then(res=>{
                    let data = res.data

                    var chartDom = document.getElementById('colleague');
                    var myChart = echarts.init(chartDom);
                    var option;
                    var legend= data.map(item=>{
                        return item.svName;
                    })

                    var seriesData = []
                    seriesData = res.data.map(item=>{
                        return Object.assign({}, { name: item.svName, value: item.consumeTimes,selected:true})
                    })

                    option = {
                        tooltip : {
                            trigger: 'item',
                            formatter: "{a} <br/>{b} : {c} ({d}%)"
                        },
                        color: ['#1891FF', '#12C3C3', '#FBCD14', '#F14864', '#8542E1', '#7DA6FF', '#4AC312', '#FB8F14', '#F148E7'],
                        legend: {
                            orient: 'horizontal',
                            icon: 'circle',
                            align: 'left',
                            bottom: '0',
                            itemWidth: 8,
                            itemHeight: 8,
                            y: '20',
                            x: 'center',
                            textStyle: {
                                color: '#999999',
                                fontSize: 12,
                                align: 'left',
                                // 文字块背景色，一定要加上，否则对齐不会生效
                                backgroundColor: "transparent",
                                rich: {
                                    b: {
                                        width: 200,
                                    },
                                },
                            },
                            data: legend
                        },
                        series : [
                            {
                                name: '场景消费占比',
                                type: 'pie',
                                radius : '45%',
                                center: ['50%', '65%'],
                                label: {
                                    normal: {

                                        // formatter: '{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ',
                                        formatter: '{b|{b}：}{c}   {per|{d}%} ',
                                        rich: {
                                            per: {
                                                color: '#eee',
                                                backgroundColor: '#334455',
                                                padding: [2, 4],
                                                borderRadius: 13,
                                                // center: ['50%', '50%']
                                                //  position: 'inside',
                                            }
                                        }
                                    }
                                },
                                data:seriesData,
                                itemStyle: {
                                    normal: {
                                    },
                                    emphasis: {
                                        shadowBlur: 10,
                                        shadowOffsetX: 0,
                                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                                    }

                                },
                                labelLine: {
                                    normal: {
                                        length: 5,
                                        length2: 1,
                                        smooth: true,
                                    }
                                },
                            }
                        ]
                    };

                    option && myChart.setOption(option);
                    myChart.on('click', function (params) {
                        var that = this
                        that.$emit('success',true);
                    });
                })

            }
        }
    }
</script>
<style scoped>
    .colleague {
        width: 100%;
        display: flex;
        justify-content: space-between;
        flex-wrap: wrap;
    }
    #colleague{
        width: 630px;
        height: 350px;
    }


</style>
