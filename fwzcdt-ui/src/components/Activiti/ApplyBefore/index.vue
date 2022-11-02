<template>
    <div>
        <el-button
            style="color: #36a4da"
            size="mini"
            type="text"
            icon="el-icon-check"
            @click="handleApply"
        >提交申请
        </el-button>
        <el-button
            style="color: #36a4da"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="_handleUpdate"
        >修改
        </el-button>
<!--        <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="_handleDelete"
        >删除
        </el-button>-->
    </div>
</template>

<script>
    import request from '@/utils/request'

    export default {
        name: "ApplyBefore",
        props: {
            /* 表格行数据 */
            row: {
                type: Object,
            },
            handleUpdate: {
                type: Function,
            },
            requestMapping: {
                type: String,
            },
        },
        methods: {
            handleApply: function () {
                if (this.row.id){
                    const id = this.row.id;
                    const requestMapping = this.requestMapping;
                    this.$confirm('是否提交ID为"' + id + '"的申请单据?', "警告", {
                        confirmButtonText: "确定",
                        cancelButtonText: "取消",
                        type: "warning"
                    }).then(function () {
                        return request({
                            url: requestMapping + '/submitApply/' + id,
                            method: 'post',
                        });
                    }).then(() => {
                        this.$emit('getList');
                        this.msgSuccess("申请成功");
                    })
                }
                if (this.row.svId){
                    const svId = this.row.svId;
                    const requestMapping = this.requestMapping;
                    this.$confirm('是否提交ID为"' + svId + '"的申请单据?', "警告", {
                        confirmButtonText: "确定",
                        cancelButtonText: "取消",
                        type: "warning"
                    }).then(function () {
                        return request({
                            url: requestMapping + '/submitApply/' + svId,
                            method: 'post',
                        });
                    }).then(() => {
                        this.$emit('getList');
                        this.msgSuccess("申请成功");
                    })
                }

            },
            _handleUpdate: function () {
                this.handleUpdate(this.row);
            },
        },
    }
</script>

<style scoped>

</style>
