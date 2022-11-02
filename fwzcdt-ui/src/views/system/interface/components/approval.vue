<template>
    <div>
        <el-card class="box-card items mb20">
            <div slot="header" class="clearfix">
                <span class="title">审批信息</span>
                <el-button class="addButton" @click="searchDetail" type="primary" size="small" plain>查看接口详情</el-button>
            </div>
            <div class="text item">
                <table class="el-table myTable" cellpadding="5" cellspacing="5" style="font-size: 16px">
                    <tr>
                        <td>申请单类型:</td>
                        <td>服务申请单</td>
                        <td>操作类型:</td>
                        <td>升级</td>
                    </tr>
                    <tr>
                        <td>项目编号:</td>
                        <td>BNX_SCENE_UCBP_20220421_银联卡整合交易平台系统接口修改</td>
                        <td>申请单归属系统:</td>
                        <td>银联卡整合交易平台</td>
                    </tr>
                    <tr>
                        <td>审核状态:</td>
                        <td>审核通过</td>
                        <td>创建时间:</td>
                        <td>2021-12-13 14:34:01</td>
                    </tr>
                    <tr>
                        <td>当前环节:</td>
                        <td></td>
                        <td>当前环节负责人:</td>
                        <td>mxy1627-马小艳</td>
                    </tr>

                </table>
            </div>
        </el-card>

        <el-card class="box-card items mb20">
            <div slot="header" class="clearfix">
                <span class="title">审批记录</span>
                <el-button class="addButton" @click="searchProcess" type="primary" size="small" plain>查看审批流程</el-button>
            </div>
            <div class="text item">
                <el-table :data="list">
                    <el-table-column label="环节" align="center" prop="link"></el-table-column>
                    <el-table-column label="审批人" align="center" prop="linkman"></el-table-column>
                    <el-table-column label="时间" align="center" prop="time"></el-table-column>
                    <el-table-column label="审批结果" align="center" prop="spjg"></el-table-column>
                    <el-table-column label="审批批注" align="center" prop="sppz"></el-table-column>
                </el-table>
            </div>
        </el-card>
        <!--审批流程-->
        <splc ref="approval_process"></splc>
        <el-dialog title="接口详情" :visible.sync="open" append-to-body>
            <interface-info></interface-info>
        </el-dialog>
    </div>
</template>

<script>

    import splc from '@/views/system/interface/components/splc'
    import interfaceInfo from '@/views/system/interface/components/interfaceInfo'

    export default {
        name: "approval",
        components:{splc,interfaceInfo},
        data(){
            return{
                open:false,

                list:[
                    {'link':'提交审批','linkman':'翁羽','time':'2021-12-13 14:34:02','spjg':'批准','sppz':'批注信息'},
                    {'link':'消费方系统负责人','linkman':'马小艳','time':'2022-02-08 10:44:29','spjg':'批准','sppz':''},
                    {'link':'服务管理人员','linkman':'邓皓之','time':'2022-02-08 16:19:47','spjg':'批准','sppz':''}
                ]
            }
        },
        methods:{
            searchDetail(){
                this.open = true
            },
            searchProcess(){
                this.$refs.approval_process.open = true;
            }
        }
    }
</script>

<style scoped lang="scss">
    .text {
        font-size: 14px;
    }

    .item {
        margin-bottom: 18px;
    }

    .clearfix:before,
    .clearfix:after {
        display: table;
        content: "";
    }
    .clearfix:after {
        clear: both
    }

    .box-card {
        width: 100%;
    }
    .items .el-card__header{
        background: #f5f7fa;
        padding-bottom: 5px;
    }
    .title{
        color: #303133;
        font-size: 18px;
        font-weight: bold;
    }
    /**按钮样式*/
    .addButton{
        float: right;
        position: relative;
        z-index: 1;
        transform: translateY(-5px);
    }

    .myTable td:nth-child(even){     //偶数行
        color:rgb(40,40,40);
        font-size: 15px;
    }
    .myTable td:nth-child(odd){      //奇数行
        color:rgb(40,40,40);
        font-weight: bold;
        font-size: 15px;
    }

</style>
