<template>
    <div>
        <el-card class="box-card items mb20">
            <div slot="header" class="clearfix">
                <span class="title">输入字段(3)</span>
            </div>
            <div class="text item">
                <el-table ref="inTable" :data="interfaceInList"  :row-style="{height:'20px'}" :cell-style="{padding:'0px'}" style="font-size: 12px" highlight-current-row>
                    <el-table-column label="字段序号" align="center" prop="id"/>
                    <el-table-column label="字段中文名" align="center" prop="znName"/>
                    <el-table-column label="字段英文名" align="center" prop="enName"/>
                    <el-table-column label="数据类型" align="center" prop="filedType"/>
                    <el-table-column label="字段长度" align="center" prop="filedLength"/>
                    <el-table-column label="小数位" align="center" prop="nameSpace"/>
                    <el-table-column label="默认值" align="center" prop="defaultValue"/>
                    <el-table-column label="是否必输" align="center" prop="isRequired"/>
                    <el-table-column label="枚举列表" align="center" prop="enumValue"/>
                    <el-table-column label="字段位置" align="center" prop="filedPath">
                        <template slot-scope="scope">
                            <el-tooltip style="margin: 4px" effect="dark" :content="scope.row.filedPath" placement="top-start" >
                                <span class="position">{{scope.row.filedPath}}</span>
                            </el-tooltip>
                        </template>
                    </el-table-column>
                    <el-table-column label="备注" align="center" prop="isSensitive"/>
                </el-table>
            </div>
        </el-card>

        <el-card class="box-card items mb20">
            <div slot="header" class="clearfix">
                <span class="title">输出字段(3)</span>
            </div>
            <div class="text item">
                <el-table ref="inTable" :data="interfaceInList"  :row-style="{height:'20px'}" :cell-style="{padding:'0px'}" style="font-size: 12px" highlight-current-row>
                    <el-table-column label="字段序号" align="center" prop="id"/>
                    <el-table-column label="字段中文名" align="center" prop="znName"/>
                    <el-table-column label="字段英文名" align="center" prop="enName"/>
                    <el-table-column label="数据类型" align="center" prop="filedType"/>
                    <el-table-column label="字段长度" align="center" prop="filedLength"/>
                    <el-table-column label="小数位" align="center" prop="nameSpace"/>
                    <el-table-column label="默认值" align="center" prop="defaultValue"/>
                    <el-table-column label="是否必输" align="center" prop="isRequired"/>
                    <el-table-column label="枚举列表" align="center" prop="enumValue"/>
                    <el-table-column label="字段位置" align="center" prop="filedPath">
                        <template slot-scope="scope">
                            <el-tooltip style="margin: 4px" effect="dark" :content="scope.row.filedPath" placement="top-start" >
                                <span class="position">{{scope.row.filedPath}}</span>
                            </el-tooltip>
                        </template>
                    </el-table-column>
                    <el-table-column label="备注" align="center" prop="isSensitive"/>
                </el-table>
            </div>
        </el-card>
    </div>
</template>

<script>
    export default {
        name: "definition",
        data(){
            return{
                interfaceInList:[
                    {'id':3,'enName':'accno','znName':'账号','filedType':'字符型','filedLength':16,'isRequired':'是','filedPath':'/Envelope/Body/getActInfo/accno'},
                    {'id':1,'enName':'accno','znName':'账号','filedType':'字符型','filedLength':16,'isRequired':'是','filedPath':'/Envelope/Body/getActInfo/accno'},
                    {'id':2,'enName':'accno','znName':'账号','filedType':'字符型','filedLength':16,'isRequired':'是','filedPath':'/Envelope/Body/getActInfo/accno'}
                ]
            }
        }
    }
</script>

<style lang="scss" scoped>
    .text {
        font-size: 14px;
    }

    .item {
        margin-bottom: 18px;
    }

    .clearfix:before,
    .clearfix:after {
        display: table;
        content: "";
    }
    .clearfix:after {
        clear: both
    }

    .box-card {
        width: 100%;
    }
    .items .el-card__header{
        background: #f5f7fa;
        padding-bottom: 5px;
    }
    .title{
        color: #303133;
        font-weight: bold;
    }
    .position{
        white-space: nowrap;  /*强制span不换行*/
        display: inline-block;  /*将span当做块级元素对待*/
        width: 100%;  /*限制宽度 可设px*/
        overflow: hidden;  /*超出宽度部分隐藏*/
        text-overflow: ellipsis;  /*超出部分以点号代替*/
    }

</style>
