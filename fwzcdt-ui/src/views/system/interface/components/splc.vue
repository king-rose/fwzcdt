<template>
    <div class="app-container">
        <el-dialog title="审批流程" :visible.sync="open" append-to-body>
            <img
                style="width: 600px;"
                :src="baseURL + '/activiti/definition/readResource?pdid=leave:2:10&resourceName=C:/ruoyi/uploadPath/processDefiniton/2022/04/20/99c1000c-aab9-42be-8bae-9c378b9eace2.bpmn.leave.png'"
            />
            <div slot="footer" class="dialog-footer" style="text-align: right;">
                <el-button @click="close">关 闭</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "splc",
        props:{
            open:{
                type: Boolean,
                default: false
            }
        },
        data(){
            return{
                baseURL:process.env.VUE_APP_BASE_API,
            }
        },
        methods:{
            close: function () {
                this.open = false
            }
        }
    }
</script>

<style scoped>

</style>
