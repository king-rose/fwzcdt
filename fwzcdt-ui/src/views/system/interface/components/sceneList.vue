<template>
    <div>
        <el-table ref="inTable" :data="interfaceInList"  :row-style="{height:'20px'}" :cell-style="{padding:'0px'}" style="font-size: 12px" highlight-current-row>
            <el-table-column label="场景码" align="center" prop="sceneCode"/>
            <el-table-column label="场景名称" align="center" prop="sceneName"/>
            <el-table-column label="服务描述" align="center" prop="serviceName"/>
            <el-table-column label="服务编号" align="center" prop="serviceId"/>
        </el-table>
    </div>
</template>

<script>
    export default {
        name: "sceneList",
        data(){
            return{
                interfaceInList:[]
            }
        }
    }
</script>

<style scoped>

</style>
