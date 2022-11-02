<template>
    <div class="app-container">
        <el-timeline v-for="(item, index)  in recordList" :key="index">
            <el-timeline-item :timestamp="item.svCreateTime" placement="top" icon="el-icon-time" color="red" size="small">
                <div style="display: flex;flex-direction: row">
                    <el-card :body-style="bodyStyle" style="width: 600px" @click.native="clickCard(index)">
                        <div style="display: flex;flex-direction: row">
                            <div class="card-left">
                                <div class="center">
                                    <div :class="item.maps.length>0? 'color-edit':'color-add'">{{item.maps.length>0? '变更':'新增'}}</div>
                                </div>
                            </div>
                            <div class="card-right">
                                <h2 style="padding-left: 15px">{{item.svName}}</h2>
                                <div class="fontstyle">项目编号:<span class="fontstyle">BNX_SCENE_UCBP</span></div>
                                <div class="fontstyle">服务名:<span class="fontstyle hoverstyle">{{item.svcName}}</span></div>
                                <div class="fontstyle">状态:<span class="fontstyle">{{item.auditState==='1'?'可用':'不可用'}}</span></div>
                                <div class="fontstyle">文件:<span class="fontstyle hoverstyle">{{item.resourceFile}}</span></div>
                                <div class="fontstyle">资源包:<span class="fontstyle hoverstyle">{{item.resourceBall}}</span></div>
                            </div>
                        </div>
                    </el-card>

                    <div class="sceneField">
                        <div style="padding: 10px">
                            <table class="el-table--border el-table table-row-cell" cellpadding="5" :style="number==index?'display:':'display:none'">
                                <thead>
                                    <td>类型</td>
                                    <td>字段英文名</td>
                                    <td>字段中文名</td>
                                    <td>输入输出</td>
                                    <td>描述</td>
                                </thead>
                                <tr v-for="record in item.maps" v-if="item.maps.length>0">
                                    <td>{{item.maps[0].changeType==='A'? '新增':item.maps[0].changeType==='U'?'修改':'删除'}}</td>
                                    <td>{{record.fieldNameEn}}</td>
                                    <td>{{record.fieldNameCn}}</td>
                                    <td>{{record.fieldInout==0?'输出':'输入'}}</td>
                                    <td>{{record.fieldChangeNote}}</td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>

            </el-timeline-item>

        </el-timeline>

        <span>加载更多</span>

    </div>
</template>

<script>

    import {getChangeInfo} from "@/api/service/oprscenario";
    export default {
        name: "bbls",
        props:{
            svId:String,
            required: true
        },
        data(){
            return{
                number:-1,
                show:true,
                recordList:[],
                bodyStyle:'',
                bodyStyle1:{
                    'border': '1px solid #25ccf7','border-left':'5px'
                },
                bodyStyle2:{
                    border: '0px solid #25ccf7','border-left':'5px'
                },
            };
        },
        created() {
            this.getVersionList(this.svId);
        },
        methods:{

            clickCard(index){
                var that = this
                if (that.number == index){
                    that.number = -1;
                    return;
                }
                that.number = index
            },
            //版本变更列表
            getVersionList(svId){
                getChangeInfo(svId).then(res=>{
                    this.recordList = res.data
                })
            }
        }
    }
</script>

<style scoped lang="scss">
    .card-left{
        width: 30%;
        height: 250px;
        text-align: center;
        line-height: 250px;
        border-right: 1px solid #e6ebf5;
    }

    .card-right{
        width: 70%;
        height: 250px;
        font-size: 16px;
        .fontstyle{
            padding-bottom: 5px;
            padding-left: 15px;
        }
    }

    .center{

        padding: 10px;
        color:#fff;
        margin: 20px auto;


        display: -webkit-box;
        -webkit-box-orient: horizontal;
        -webkit-box-pack: center;
        -webkit-box-align: center;

        display: -moz-box;
        -moz-box-orient: horizontal;
        -moz-box-pack: center;
        -moz-box-align: center;


    }

    .hoverstyle{
        color: #4ab7bd;
    }
    .hoverstyle:hover{
        cursor: pointer;
    }
    .color-edit{
        color: white;
        width: 100px;
        height: 100px;
        border-radius: 50%;
        font-size: 20px;
        line-height: 100px;
        background: red;
    }
    .color-add{
        color: white;
        width: 100px;
        height: 100px;
        border-radius: 50%;
        font-size: 20px;
        line-height: 100px;
        background: #25ccf7;
    }

    .sceneField{
        width: 600px;
        margin-left: 15px;
        border-top: 1px solid #dfe4ed;
        border-right: 1px solid #dfe4ed;
        height:200px;
        overflow-y: scroll;
    }
    .sceneField::-webkit-scrollbar {
        display: none;
    }
</style>
