<template>
    <div>
        <el-table ref="inTable" :data="interfaceInList"  :row-style="{height:'20px'}" :cell-style="{padding:'0px'}" style="font-size: 12px" highlight-current-row>
            <el-table-column label="消费方" align="center" prop="consumer"/>
            <el-table-column label="服务编号" align="center" prop="serviceId"/>
            <el-table-column label="服务名称" align="center" prop="serviceName"/>
            <el-table-column label="场景码" align="center" prop="sceneCode"/>
            <el-table-column label="场景名称" align="center" prop="sceneName"/>
            <el-table-column label="提供方" align="center" prop="provider"/>
            <el-table-column label="消费时间" align="center" prop="consumeTime"/>
        </el-table>
    </div>
</template>

<script>
    export default {
        name: "consumption",
        data(){
            return{
                interfaceInList:[]
            }
        }
    }
</script>

<style scoped>

</style>
