<template>
    <div>
        <!--頭部-->
        <div class="header">
            <div style="margin: 10px auto">
                <span>服务场景消费列表</span>
            </div>
            <div style="width: 90%;margin: 0 auto">
                <el-divider></el-divider>
                <el-row>
                    <el-col :span="12">
                        <el-form ref="service-scenes" label-position="left" :model="scenesForm" class="form">
                            <el-form-item label="服务提供方:">
                                <el-select filterable clearable v-model="value">
                                    <el-option label="--请选择--" value="-1"/>
                                    <el-option label="CBTS-核心交易系统" value="1"/>
                                </el-select>
                            </el-form-item>
                        </el-form>
                    </el-col>
                </el-row>
            </div>
            <div style="width: 95%;margin: 0 auto;margin-bottom: 20px">
                <el-table :data="scenesList" border>
                    <el-table-column label="服务编号" prop="serviceId"></el-table-column>
                    <el-table-column label="服务名称" prop="serviceName"></el-table-column>
                    <el-table-column label="场景码" prop="senceCode"></el-table-column>
                    <el-table-column label="场景名称" prop="senceName"width="170px">
                        <template slot-scope="scope">
                            <router-link :to="{ path:'/xfqdList/detail'}">
                                <span class="cell-hover">{{scope.row.senceName}}</span>
                            </router-link>
                        </template>
                    </el-table-column>
                    <el-table-column label="接口码" prop="jieKouId" width="180px"></el-table-column>
                    <el-table-column label="关联接口所属技术" prop="gljkssxt"></el-table-column>
                    <el-table-column label="状态" prop="type"></el-table-column>
                </el-table>
                <pagination
                    v-show="total>=0"
                    :total="total"
                    :page.sync="queryParams.pageNum"
                    :limit.sync="queryParams.pageSize"
                    @pagination="getList"
                />
            </div>
        </div>
        <!--頭部-->
        <div class="header">
            <div style="margin: 10px auto">
                <span>接口消费列表</span>
            </div>

            <div style="width: 90%;margin: 0 auto">
                <el-divider></el-divider>
            </div>
            <div style="width: 95%;margin: 0 auto;margin-bottom: 20px">
                <el-table :data="interfaceList" border>
                    <el-table-column label="提供方" prop="provider"></el-table-column>
                    <el-table-column label="借口交易码" prop="etCode"></el-table-column>
                    <el-table-column label="接口名称" prop="interfaceName"></el-table-column>
                    <el-table-column label="状态" prop="status"></el-table-column>
                </el-table>
                <pagination
                    v-show="interTotal>=0"
                    :total="interTotal"
                    :page.sync="queryParams.pageNum"
                    :limit.sync="queryParams.pageSize"
                    @pagination="getInterList"
                />
            </div>
        </div>
    </div>

</template>

<script>
    export default {
        name: "service-scenes",
        data(){
            return{
                scenesForm:{},
                value:null,
                scenesList:[],
                interfaceList:[],
                //查询参数
                queryParams:{
                    pageNum:1,
                    pageSize:10
                },
                total:3,
                interTotal:0,
            }
        },
        created() {
            this.getInterList();
            this.getList();
        },
        methods: {
            getList(){
                this.scenesList = [
                    {
                        serviceId:"ChargeAcct",
                        serviceName:"统一记账",
                        senceCode:"F01",
                        senceName:"核心多借多贷通用记账",
                        jieKouId:"MbsdCore-1000-039001",
                        gljkssxt:"核心交易系统",
                        type:"已发布"
                    },
                    {
                        serviceId:"ChargeAcct",
                        serviceName:"统一记账",
                        senceCode:"F08",
                        senceName:"内部帐利息回单记账",
                        jieKouId:"MbsdCore-1000-049075",
                        gljkssxt:"核心交易系统",
                        type:"已发布"
                    },
                    {
                        serviceId:"ChargeAcct",
                        serviceName:"记账状态查询",
                        senceCode:"Q01",
                        senceName:"核心记账状态查询",
                        jieKouId:"MbsdCore-1400-3001",
                        gljkssxt:"核心交易系统",
                        type:"已发布"
                    },
                ]
            },
            getInterList(){

            }
        }
    }

</script>

<style scoped>
    .header{
        margin: 15px 0;
        border: #dcdfe6 1px solid;
        text-align: center;
        font-size: 20px;
        font-weight: normal;
    }
    .cell-hover{
        color: #17b9ba;
    }
    .cell-hover:hover{
        color: #2852a5;
    }
</style>
