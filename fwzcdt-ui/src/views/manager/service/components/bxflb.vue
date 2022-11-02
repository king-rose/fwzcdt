<template>
    <div class="app-container">
        <el-table :data="list">
            <el-table-column type="index" :index="calcIndex" label="序号" width="50" align="center"> </el-table-column>
            <el-table-column label="系统编号" prop="sysCode" align="center"></el-table-column>
            <el-table-column label="系统名称" prop="sysName" align="center"></el-table-column>
            <el-table-column label="系统负责人" prop="userName" align="center"></el-table-column>
            <el-table-column label="所属部门" prop="deptName" align="center"></el-table-column>
            <el-table-column label="系统概述" prop="sysDes" align="center"></el-table-column>
        </el-table>
        <pagination
            v-show="total>0"
            :total="total"
            :page.sync="queryParams.pageNum"
            :limit.sync="queryParams.pageSize"
            @pagination="getList"
        />
    </div>
</template>

<script>
    import {getConsumeInfo} from "@/api/service/oprscenario";

    export default {
        name: "bxflb",
        props:{
            svId:String,
            required: true
        },
        data(){
            return{
                total:3,
                queryParams:{
                    pageNum:1,
                    pageSize:10
                },

                //表格数据
                list:[]
            }
        },
        created() {
            this.getList(this.svId)
        },
        methods:{
            getList(svId){
                getConsumeInfo(svId).then(res=>{
                    this.list = res.data
                    this.total = res.data.length
                })
            },
            calcIndex(index) {
                return (this.queryParams.pageNum - 1) * this.queryParams.pageSize + index + 1
            },
        }
    }
</script>

<style scoped>

</style>
