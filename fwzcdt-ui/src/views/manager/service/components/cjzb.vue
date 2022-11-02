<template>
    <div>
        <div class="title">场景指标信息</div>
        <div class="footer">
            <el-row :gutter="5">
                <el-col :span="3"><div class="grid-content-left">服务窗口时间:</div></el-col>
                <el-col :span="3"><div class="grid-content"></div>{{form.sceneWindowTime}}</el-col>
                <el-col :span="3"><div class="grid-content-left">服务超时时间:</div></el-col>
                <el-col :span="3"><div class="grid-content">{{form.sceneTimeout}}</div></el-col>
                <el-col :span="3"><div class="grid-content-left">并发用户数:</div></el-col>
                <el-col :span="3"><div class="grid-content"></div>{{form.oneTimeUserNum}}</el-col>
                <el-col :span="3"><div class="grid-content-left">TPS-每秒交易笔数:</div></el-col>
                <el-col :span="3"><div class="grid-content"></div>{{form.tradePerSecond}}</el-col>
            </el-row>

            <el-row :gutter="5">
                <el-col :span="3"><div class="grid-content-left">服务场景生效时间:</div></el-col>
                <el-col :span="3"><div class="grid-content">{{form.onlineDate}}</div></el-col>
                <el-col :span="3"><div class="grid-content-left">服务场景失效时间:</div></el-col>
                <el-col :span="3"><div class="grid-content">{{form.offlineDate}}</div></el-col>
                <el-col :span="3"><div class="grid-content-left">优先级:</div></el-col>
                <el-col :span="3"><div class="grid-content">{{form.scenePriority}}</div></el-col>
            </el-row>
        </div>

    </div>
</template>

<script>
    import {getSceneQuotaInfo} from "@/api/service/oprscenario";
    export default {
        name: "cjzb",
        props:{
            svId:String,
            required: true
        },
        data(){
            return{
                form:{}
            };
        },
        created() {
            this.getSceneQuotaInfo(this.svId)
        },
        methods :{
            getSceneQuotaInfo(svId){
                getSceneQuotaInfo(svId).then(res=>{
                    this.form = res.data
                })
            }
        }
    }
</script>


<style scoped lang="scss">

    .el-row {
        margin-bottom: 20px;
        &:last-child {
            margin-bottom: 0;
        }
    }
    .el-col {
        border-radius: 4px;
    }

    .title{
        padding: 8px;
        font-size: 16px;
        font-weight: bold;
        color: #747d8c;
        line-height: 40px;
        border: 1px solid #ced6e0;
        border-top-left-radius: 5px;
        border-top-right-radius: 5px;
    }
    .footer{
        border-bottom:1px solid #ced6e0;
        border-left:1px solid #ced6e0;
        border-right:1px solid #ced6e0;
        border-bottom-left-radius: 5px;
        border-bottom-right-radius: 5px;
    }

    .grid-content {
        padding: 8px;
        font-size: 15px;
        min-height: 36px;
        line-height: 36px;
    }
    .grid-content-left{
        width: 145px;
        padding: 8px;
        font-size: 15px;
        min-height: 36px;
        line-height: 36px;
    }
</style>
