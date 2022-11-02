<template>
    <div class="index">
        <ball></ball>
        <!-- 头部 -->
        <el-dialog
			:fullscreen="true"
            destroy-on-close
            @open="initChat"
            :before-close="handle_dialog_close"
            title="系统关系"
            :visible="open"
            width="70%"
            :close-on-click-modal="true">
			<div id="chart"></div>
        </el-dialog>

        <!-- 图标 -->
        <div class="index-centent">
            <el-row :gutter="8">
                <el-col :md="15">
                    <!-- 项目 -->
                    <div class="grid-content">
                        <div class="index-centent-title">
                            <div class="index-centent-title-left">
                                <svg-icon icon-class="basic" class="index-centent-title-left-icon"></svg-icon>
                                系统信息
                            </div>
                            <div class="index-centent-title-right">
                                全部
                            </div>
                        </div>
                        <div class="index-centent-box">
                            <Project/>
                        </div>
                    </div>
                    <!-- 动态 -->
                    <div class="grid-content">
                        <div class="index-centent-title">
                            <div class="index-centent-title-left">
                                <svg-icon icon-class="qs" class="index-centent-title-left-icon"></svg-icon>
                                服务资产变化趋势
                            </div>
                            <div class="index-centent-title-right">
								<span class="index-centent-title-right-span" v-for="(title,index) in timeTitle" @click="searchParam(index)">{{title}}</span>
                            </div>
                        </div>
                        <div class="index-centent-box">
                            <DynamicBox v-bind:date="date" ref="dateClick"/>
                        </div>
                    </div>
                </el-col>
                <el-col :md="9">
                    <div class="grid-content">
                        <div class="index-centent-title">
                            <div class="index-centent-title-left">
                                <svg-icon icon-class="react" class="index-centent-title-left-icon"></svg-icon>
                                服务场景占比
                            </div>
                            <div class="index-centent-title-right">
                                <!-- 全部动态 -->
                            </div>
                        </div>
                        <div class="index-centent-box">
							<fast v-bind:date="date" :open.sync="open" :proSysId.sync="proSysId"></fast>
                        </div>
                    </div>
                    <div class="grid-content">
                        <div class="index-centent-title">
                            <div class="index-centent-title-left">
                                <svg-icon icon-class="xf" class="index-centent-title-left-icon"></svg-icon>
                                消费关系占比
                            </div>
                            <div class="index-centent-title-right">
                                <!-- 全部动态 -->
                            </div>
                        </div>
                        <div class="index-centent-box">
                            <Colleague/>
                        </div>
                    </div>
                </el-col>
            </el-row>
        </div>
    </div>
</template>

<script>
    import ball from '@/components/task/float' //动态
    import {mapGetters} from 'vuex'
    import echarts from 'echarts'
    import Project from '@/views/home/Index/project' // 项目
    import DynamicBox from '@/views/home/Index/dynamicBox' //动态
    import Colleague from '@/views/home/Index/colleague'
	import Fast from '@/views/home/Index/fast'
    import {sceneRatioChart,serviceScene} from  "@/api/system/list"
    import {consumeRatioData} from  "@/api/service/mappingrelation"

    export default {
        components: {echarts, Project, DynamicBox, Fast, ball, Colleague},
        data() {
            return {
                date:0,
                //弹窗展示
                open:false,
				open_1:false,
                //折线图时间选择
                timeTitle:['年','季','月'],
                //查询参数
				proSysId:'',
				proSysName:'',
				reqSysId:'',
				reqSysName:''
            }
        },

        computed: {
            ...mapGetters([
                'avatar'
            ]),
        },
        methods: {
            //关闭弹框
            handle_dialog_close() {
				this.open = false
            },
            searchParam(param) {
                this.date = param
                this.$refs.dateClick.chartInit(param)
            },

            initChat(){
                consumeRatioData(this.proSysId).then(res=>{
                    const seriesData=[],links=[]
                    const list = res.data.list
                    const proSysNames = res.data.proSysNames
                    const reqSysNames = res.data.reqSysNames

                    proSysNames.forEach(e=>{
                        seriesData.push(
                            {
                                name: e,
                                symbolSize: 17.08800749063506
                            },
                        )
                    })
                    reqSysNames.forEach(e=>{
                        seriesData.push(
                            {
                                name: e,
                                symbolSize: 17.08800749063506
                            },
                        )
                    })

                    var chartDom = document.getElementById('chart');
                    var myChart = echarts.init(chartDom);

                    list.forEach(e=>{
                        links.push(
                            {
                                'source': e.reqSysName,
                                'target': e.proSysName,
                                'mappingId':e.mappingId,
								'proSysId':e.proSysId,
								'reqSysId':e.reqSysId,
                                'symbol': ['circle', 'arrow'],
                                'symbolSize': [5, 15],
                                'lineStyle': {
                                    width: 4.7071067811865476
                                }
                            },
                        )
                    })
                    //该图的数据抓取及可视化教程：https://gitee.com/ni1o1/pygeo-tutorial/blob/master/%E6%8A%93%E5%BE%AE%E5%8D%9A!%E5%BE%AE%E5%8D%9A%E8%AF%84%E8%AE%BA%E5%85%B3%E7%B3%BB%E5%8F%AF%E8%A7%86%E5%8C%96.ipynb
                    var option = {
                        "animation": true,
                        "animationThreshold": 2000,
                        "animationDuration": 1000,
                        "animationEasing": "cubicOut",
                        "animationDelay": 0,
                        "animationDurationUpdate": 300,
                        "animationEasingUpdate": "cubicOut",
                        "animationDelayUpdate": 0,
                        "color": [
                            "#c23531","#2f4554", "#61a0a8", "#d48265", "#749f83", "#ca8622", "#bda29a", "#6e7074", "#546570",
                            "#c4ccd3", "#f05b72", "#ef5b9c", "#f47920", "#905a3d", "#fab27b", "#2a5caa", "#444693", "#726930",
                            "#b2d235", "#6d8346", "#ac6767", "#1d953f", "#6950a1", "#918597"
                        ],
                        "series": [
                            {
                                "type": "graph",
                                "name": "",
                                "layout": "force",
                                "symbolSize": 10,
                                edgeSymbol: ['circle', 'arrow'],
								"edgeSymbolSize": [10,10],
                                "circular": {
                                    "rotateLabel": false
                                },
                                "force": {
                                    "repulsion": 550,
                                    "edgeLength": 300,
                                    "gravity": 0.1
                                },
                                "label": {
                                    "show": true,
                                    "position": "top",
                                    "margin": 8
                                },
                                "lineStyle": {
                                    "show": true,
                                    "width": 12,
                                    "opacity": 1,
                                    "curveness": 0,
                                    "type": "solid"
                                },
                                "roam": true,
                                "draggable": false,
                                "focusNodeAdjacency": true,
                                "data": seriesData,
                                "edgeLabel": {
                                    "show": false,
                                    "position": "top",
                                    "margin": 8
                                },
                                "edgeSymbolSize": 10,
                                "links": links
                            }
                        ],
                        "legend": [
                            {
                                "data": [],
                                "selected": {},
                                "show": true,
                                "itemGap": 10,
                                "itemWidth": 25,
                                "itemHeight": 14
                            }
                        ],
                        "tooltip": {
                            "show": true,
                            "trigger": "item",
                            "triggerOn": "mousemove|click",
                            "axisPointer": {
                                "type": "line"
                            },
                            "textStyle": {
                                "fontSize": 14
                            },
                            "borderWidth": 0
                        },
                       /* "title": [
                            {

                                "padding": 5,
                                "itemGap": 10,

                                text: "系统关系图",
                            }
                        ]*/
                    }
                    option && myChart.setOption(option);
                    var that = this
                    myChart.on('click',function (params) {
				/*		that.reqSysId = params.data.reqSysId;
						that.proSysId = params.data.proSysId;
						that.reqSysName = params.data.source;
						that.proSysName = params.data.target;
						that.open_1 = true;*/
                        if (params.data.reqSysId && params.data.proSysId){
                            that.handle_dialog_close()

                            //跳转消费列表页
                            that.$router.push({path: `service/consume`, query: {reqSysId: params.data.reqSysId,proSysId: params.data.proSysId}})
                        }
                    })
                })

            }
        }
    }
</script>

<style scoped>
    .index {
        width: 100%;
        min-height: 100%;
        background: #f5f5f5;
    }

    .index-head {
        width: 100%;
        /* height: 133px; */
        padding: 16px 32px 0 32px;
        box-sizing: border-box;
        background: rgba(255, 255, 255, .7);
        border-bottom: 1px solid #dcdfe6;
    }

    .index-head-title {
        width: 100%;
        height: 30px;
        line-height: 30px;
        font-size: 20px;
        color: #292929;
        margin-bottom: 16px;
    }

    .index-head-centent-left {
        width: 100%;
        height: 70px;
        display: flex;
        justify-content: flex-start;
        align-items: center;
    }

    .index-head-centent-left-text {
        height: 56px;
    }

    .index-head-centent-left-text p:nth-child(1) {
        margin-bottom: 7px;
        font-size: 20px;
        color: #292929;
        letter-spacing: 0.1em;

    }

    .index-head-centent-left-text p:nth-child(2) {
        font-size: 12px;
        color: #999999;
        letter-spacing: 0.1em;
    }

    .index-head-centent-right {
        width: 100%;
        height: 70px;
        display: flex;
        justify-content: flex-end;
        align-items: center;
    }

    .index-head-centent-right-list {
        height: 56px;
        cursor: pointer;
        margin-left: 20px;
    }

    .index-head-centent-right-list-icon {
        font-size: 14px;
        display: flex;
        margin-bottom: 5px;
        color: #808695;
    }

    .index-head-centent-right-list-icon-is {
        width: 14px;
        height: 14px;
        fill: currentColor;
        margin-right: 5px;
        padding: 5px;
        border-radius: 12px;
    }

    .index-head-centent-right-list-text {
        height: 28px;
        line-height: 28px;
        font-weight: bold;
        text-align: right;
        font-size: 20px;
    }

    .index-centent {
        width: 100%;
        min-height: 100%;
        padding: 25px;
        box-sizing: border-box;


    }


    .el-col {
        border-radius: 4px;
    }

    .grid-content {
        border-radius: 4px;
        min-height: 36px;
        background: #ffffff;
        width: 100%;
        margin-bottom: 25px;
    }

    .index-centent-title {
        width: 100%;
        height: 52px;
        border-bottom: 1px solid #dcdfe6;
        display: flex;
        align-items: center;
        flex-wrap: wrap;
        padding: 0 15px;
        box-sizing: border-box;
        justify-content: space-between;
        font-size: 14px;
    }

    .index-centent-title-left {
        display: flex;
        justify-content: flex-start;
        font-size: 16px;
        /*color: #5ea2d3;*/
        /* border-radius: 11px;
        overflow: hidden; */
    }

    .index-centent-title-left-icon {
        width: 20px;
        height: 20px;
        font-size: 12px;
        color: #1890FF;
        padding: 2px;
        fill: currentColor;
        margin-right: 10px;
    }

    .index-centent-title-right {
        color: #3CA0FD;
        cursor: pointer;
    }
	.index-centent-title-right-span{
		padding-left: 10px;
	}
	.index-centent-title-right-span:hover{
		text-decoration: underline;
	}

    .index-centent-box {
        width: 100%;
        padding: 10px 10px 0;
        box-sizing: border-box;
    }


</style>

<style lang="scss">
    #chart{
        width: 800px;
        height: 800px;
		margin: auto;
    }
</style>
