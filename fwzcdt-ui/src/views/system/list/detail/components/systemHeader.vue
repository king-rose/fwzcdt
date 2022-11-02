<template>
    <div>
        <el-row :gutter="10" class="mb8">
            <el-col :span="1.5">
                <el-button
                    type="primary"
                    plain
                    icon="el-icon-plus"
                    size="mini"
                    @click="handleQuery"
                >查看详情</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="success"
                    plain
                    icon="el-icon-edit"
                    size="mini"
                    :disabled="single"
                    @click="handleUpdate"
                >维护</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="danger"
                    plain
                    icon="el-icon-delete"
                    size="mini"
                    :disabled="multiple"
                    @click="handleDelete"
                >删除</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="info"
                    plain
                    icon="el-icon-remove"
                    size="mini"
                    @click="offLine"
                >下线</el-button>
            </el-col>
        </el-row>

        <el-table v-loading="loading" :data="list" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="50" align="center" />
            <el-table-column label="系统编号" align="center" key="userId" prop="userId" />
            <el-table-column label="系统名称" align="center" key="userName" prop="userName" />
            <el-table-column label="报文头编码" align="center" key="nickName" prop="nickName" />
            <el-table-column label="请求路径" align="center" key="deptName" prop="dept.deptName"/>
            <el-table-column label="响应路径" align="center" key="phonenumber" prop="phonenumber" />
        </el-table>
        <pagination
            v-show="total>=0"
            :total="total"
            :page.sync="queryParams.pageNum"
            :limit.sync="queryParams.pageSize"
            @pagination="getList"
        />
    </div>

</template>

<script>
    export default {
        name: "systemHeader",
        data(){
            return{
                loading:false,
                // 非单个禁用
                single: true,
                // 非多个禁用
                multiple: true,
                total:0,
                queryParams:{
                    pageNum:1,
                    pageSize:10
                },
                list:[]
            }
        },
        created() {
            this.getList();
        },
        methods :{
            getList(){

            },
            // 多选框选中数据
            handleSelectionChange(selection) {
                this.ids = selection.map(item => item.userId);
                this.single = selection.length != 1;
                this.multiple = !selection.length;
            },
            //详情
            handleQuery(){

            },
            //维护
            handleUpdate(){

            },
            //删除
            handleDelete(){

            },
            //下线
            offLine(){

            }
        }
    }
</script>

<style scoped>

</style>
