<template>
    <div>
        <!-- 审批对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="120px">
               <!-- <el-form-item label="实际开始时间" prop="realityStartTime" v-if="taskName==='服务管理人员' || type !== 'todo'">
                    <el-date-picker clearable size="small" style="width: 360px"
                                    v-model="form.realityStartTime"
                                    type="date"
                                    value-format="yyyy-MM-dd"
                                    placeholder="选择实际开始时间">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="实际结束时间" prop="realityEndTime" v-if="taskName==='服务管理人员' || type !== 'todo'">
                    <el-date-picker clearable size="small" style="width: 360px"
                                    v-model="form.realityEndTime"
                                    type="date"
                                    value-format="yyyy-MM-dd"
                                    placeholder="选择实际结束时间">
                    </el-date-picker>
                </el-form-item>-->
                <el-form-item label="服务场景码：" prop="svCode">
                    <el-input v-model="form.svCode" readonly/>
                </el-form-item>
                <el-form-item label="服务场景名称：" prop="applyUserName">
                    <el-input v-model="form.svName" readonly/>
                </el-form-item>
                <el-form-item label="申请人ID" prop="applyUserName">
                    <el-input v-model="form.applyUserId" readonly/>
                </el-form-item>
                <el-form-item label="申请人" prop="applyUserName">
                    <el-input v-model="form.applyUserName" readonly/>
                </el-form-item>
                <el-form-item label="申请时间" prop="applyTime">
                    <el-date-picker clearable size="small" style="width: 360px"
                                    v-model="form.applyTime"
                                    type="date"
                                    value-format="yyyy-MM-dd"
                                    readonly>
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="备注" prop="updateReason">
                    <el-input v-model="form.updateReason" type="textarea" :readonly="true"/>
                </el-form-item>
            </el-form>
            <view-verify
                :open.sync="open"
                :row="row"
                :taskId="taskId"
                :type="type"
            />
        </el-dialog>
    </div>
</template>

<script>
    import ViewVerify from "@/components/Activiti/ViewVerify/index";
    export default {
        name: "Audit",
        components: {
            ViewVerify,
        },
        props: {
            row: {
                type: Object,
            },
            taskId: {
                type: String,
            },
            taskName: {
                type: String,
            },
            type: {
                type: String,
                default: 'none',
            },
        },
        data() {
            return {
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 表单参数
                form: this.row,
                // 表单校验
                rules: {
                    realityStartTime: [
                        {required: true, message: "实际开始时间不能为空", trigger: ["change","blur"]}
                    ],
                    realityEndTime: [
                        {required: true, message: "实际结束时间不能为空", trigger: ["change","blur"]}
                    ]
                }
            };
        },
    };
</script>
