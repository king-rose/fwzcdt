<template>
    <div class="app-container">
        <el-table :data="inList" :height="inList.length>5?'400px':'100px'">
            <el-table-column label="序号" min-width="50px"></el-table-column>
            <el-table-column label="字段英文名" min-width="170px" prop="sfEnName" align="center"></el-table-column>
            <el-table-column label="字段中文名" min-width="150px" prop="sfCnName" align="center"></el-table-column>
            <el-table-column label="数据类型" min-width="150px" prop="sfDataType" align="center">
                <template slot-scope="scope">
                    {{scope.row.sfDataType=='2'?'String':'array'}}
                </template>
            </el-table-column>
            <el-table-column label="字段长度" min-width="150px" prop="sfLength" align="center"></el-table-column>
            <el-table-column label="字段长度类型" min-width="150px" prop="sfLengthType" align="center">
                <template slot-scope="scope">
                    {{scope.row.sfLengthType=='0'?'最长':scope.row.sfLengthType=='1'?'定长':'数组'}}
                </template>
            </el-table-column>
            <el-table-column label="字段小数位" min-width="150px" prop="sfFraction" align="center"></el-table-column>
            <el-table-column label="字段默认值" min-width="150px" prop="sfDefault" align="center"></el-table-column>
            <el-table-column label="是否必输" min-width="150px" prop="sfIsEnssential" align="center">
                <template slot-scope="scope">
                    {{scope.row.sfIsEnssential=='0'?'否':'是'}}
                </template>
            </el-table-column>
            <el-table-column label="循环次数" min-width="150px" prop="sfCycleTimes" align="center"></el-table-column>
            <el-table-column label="数据格式" min-width="150px" prop="sfFormat" align="center"></el-table-column>
            <el-table-column label="枚举列表" min-width="150px" prop="sfEnum" align="center"></el-table-column>
            <el-table-column label="字段描述" min-width="200px" prop="sfDes" align="center"></el-table-column>
            <el-table-column label="字段路径" min-width="200px" prop="sfPath" align="center"></el-table-column>
        </el-table>
        <pagination
            v-show="inTotal>0"
            :total="inTotal"
            :page.sync="queryParamsIn.pageNum"
            :limit.sync="queryParamsIn.pageSize"
            @pagination="getList"
        />
        <el-table :data="outList" :height="outList.length>5?'400px':'100px'">
            <el-table-column label="序号" min-width="50px"></el-table-column>
            <el-table-column label="字段英文名" min-width="170px" prop="sfEnName"></el-table-column>
            <el-table-column label="字段中文名" min-width="150px" prop="sfCnName"></el-table-column>
            <el-table-column label="数据类型" min-width="150px" prop="sfDataType">
                <template slot-scope="scope">
                    {{scope.row.sfDataType=='2'?'String':'array'}}
                </template>
            </el-table-column>
            <el-table-column label="字段长度" min-width="150px" prop="sfLength"></el-table-column>
            <el-table-column label="字段长度类型" min-width="150px" prop="sfLengthType">
                <template slot-scope="scope">
                    {{scope.row.sfLengthType=='0'?'最长':scope.row.sfLengthType=='1'?'定长':'数组'}}
                </template>
            </el-table-column>
            <el-table-column label="字段小数位" min-width="150px" prop="sfFraction"></el-table-column>
            <el-table-column label="字段默认值" min-width="150px" prop="sfDefault"></el-table-column>
            <el-table-column label="是否必输" min-width="150px" prop="sfIsEnssential">
                <template slot-scope="scope">
                    {{scope.row.sfIsEnssential=='0'?'否':'是'}}
                </template>
            </el-table-column>
            <el-table-column label="循环次数" min-width="150px" prop="sfCycleTimes"></el-table-column>
            <el-table-column label="数据格式" min-width="150px" prop="sfFormat"></el-table-column>
            <el-table-column label="枚举列表" min-width="150px" prop="sfEnum"></el-table-column>
            <el-table-column label="字段描述" min-width="200px" prop="sfDes"></el-table-column>
            <el-table-column label="字段路径" min-width="200px" prop="sfPath"></el-table-column>
        </el-table>
        <pagination
            v-show="outTotal>0"
            :total="outTotal"
            :page.sync="queryParamsOut.pageNum"
            :limit.sync="queryParamsOut.pageSize"
            @pagination="getList"
        />
    </div>
</template>

<script>
    import {getScenarioDefinedIn,getScenarioDefinedOut} from "@/api/service/oprscenario";
    export default {
        name: "cjdy",
        props:{
            svId:String,
            required: true
        },
        data(){
            return{
                inList:[],
                outList:[],
                inTotal:0,
                outTotal:0,
                queryParamsIn:{
                    svId:'',
                    pageNum:1,
                    pageSize:10,
                },
                queryParamsOut:{
                    svId:'',
                    pageNum:1,
                    pageSize:10,
                }
            }
        },
        created() {
            this.queryParamsIn.svId = this.svId
            this.queryParamsOut.svId = this.svId
            this.getList();
        },
        methods:{
            getList(){
                getScenarioDefinedIn(this.queryParamsIn).then(res=>{
                    this.inList = res.rows
                    this.inTotal = res.total
                })
                getScenarioDefinedOut(this.queryParamsOut).then(res=>{
                    this.outList = res.rows
                    this.outTotal = res.total
                })
            },
        }
    }
</script>

<style scoped>

</style>
