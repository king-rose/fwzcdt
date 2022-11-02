<template>
    <!--頭部-->
    <div class="header">
        <div style="margin: 10px auto">
            <span>被消费服务场景列表</span>
        </div>

        <div style="width: 90%;margin: 0 auto">
            <el-divider></el-divider>
            <el-row>
                <el-col :span="12">
                    <el-form ref="service-scenes" label-position="left" class="form">
                        <el-form-item label="服务消费方:">
                            <el-select v-model="value" filterable clearable>
                                <el-option label="--请选择--" value="-1"/>
                            </el-select>
                        </el-form-item>
                    </el-form>
                </el-col>
            </el-row>
        </div>
        <div style="width: 95%;margin: 0 auto;margin-bottom: 20px">
            <el-table :data="scenesList" border>
                <el-table-column label="消费方编号" prop="serviceId"></el-table-column>
                <el-table-column label="消费方名称" prop="serviceName"></el-table-column>
                <el-table-column label="服务编号" prop="serviceId"></el-table-column>
                <el-table-column label="服务名称" prop="serviceId"></el-table-column>
                <el-table-column label="场景码" prop="senceCode"></el-table-column>
                <el-table-column label="场景名称" prop="serviceId"></el-table-column>
                <el-table-column label="状态" prop="serviceId"></el-table-column>
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
</template>

<script>
    export default {
        name: "wasConsumption",
        data(){
            return{
                value:null,
                scenesList:[],
                total:0,
                //查询参数
                queryParams:{
                    pageNum:1,
                    pageSize:10
                },
            }
        },
        created() {
            this.getList();
        },
        methods: {
            getList(){

            },
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
</style>
