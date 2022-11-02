<template>
    <div class="main">
        <el-form>
            <el-row :gutter="15" class="mb8">
                <el-col :span="8">
                    <el-row>
                        <el-col :span="5">
                            <span>接口码:</span>
                        </el-col>
                        <el-col :span="19">
                            <span>A01</span>
                        </el-col>
                    </el-row>
                </el-col>
                <el-col :span="8">
                    <el-row>
                        <el-col :span="5">
                            <span>接口名称:</span>
                        </el-col>
                        <el-col :span="19">
                            <span>账户余额查询</span>
                        </el-col>
                    </el-row>
                </el-col>
                <el-col :span="8">
                    <el-row>
                        <el-col :span="5">
                            <span>版本号:</span>
                        </el-col>
                        <el-col :span="19">
                            <span>1.0</span>
                        </el-col>
                    </el-row>
                </el-col>
            </el-row>

            <el-row :gutter="15" class="mb8">
                <el-col :span="8">
                    <el-row>
                        <el-col :span="5">
                            <span>状态:</span>
                        </el-col>
                        <el-col :span="19">
                            <span>已发布</span>
                        </el-col>
                    </el-row>
                </el-col>
                <el-col :span="8">
                    <el-row>
                        <el-col :span="5">
                            <span>投产状态:</span>
                        </el-col>
                        <el-col :span="19">
                            <span>设计态</span>
                        </el-col>
                    </el-row>
                </el-col>
                <el-col :span="8">
                    <el-row>
                        <el-col :span="5">
                            <span>通讯协议:</span>
                        </el-col>
                        <el-col :span="19">
                            <span>TCP</span>
                        </el-col>
                    </el-row>
                </el-col>
            </el-row>

            <el-row :gutter="15" class="mb8">
                <el-col :span="8">
                    <el-row>
                        <el-col :span="5">
                            <span>报文格式:</span>
                        </el-col>
                        <el-col :span="19">
                            <span>XML</span>
                        </el-col>
                    </el-row>
                </el-col>
                <el-col :span="8">
                    <el-row>
                        <el-col :span="5">
                            <span>接口描述:</span>
                        </el-col>
                        <el-col :span="19">
                            <span>账户余额查询</span>
                        </el-col>
                    </el-row>
                </el-col>
                <el-col :span="8">
                    <el-row>
                        <el-col :span="5">
                            <span>接口文件:</span>
                        </el-col>
                        <el-col :span="19">
                            <span title="ESB项目_接口定义_ZHT03_张涛测试03_A001账户余额查询_v1.0.xlsx" class="file">ESB项目_接口定义_ZHT03_张涛测试03_A001账户余额查询_v1.0.xlsx</span>
                        </el-col>
                    </el-row>
                </el-col>
            </el-row>


        </el-form>
    </div>
</template>

<script>
    export default {
        name: "attr"
    }
</script>

<style>
    .main{
        padding: 10px;
        border: #d9d9d9 solid 1px;
        border-radius: 3px;
        font-size: 12px;
        color: #333333;
    }
    .file{
        color: #07b6c6;
        white-space: nowrap;  /*强制span不换行*/
        display: inline-block;  /*将span当做块级元素对待*/
        width: 100%;  /*限制宽度 可设px*/
        overflow: hidden;  /*超出宽度部分隐藏*/
        text-overflow: ellipsis;  /*超出部分以点号代替*/
    }
    .file:hover{
        color: #39cad5;
        cursor:pointer;

    }
</style>
