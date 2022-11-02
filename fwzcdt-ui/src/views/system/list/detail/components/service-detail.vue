<template>
        <!--頭部-->
            <div style="width: 95%;margin: 0 auto;">
                <el-table :data="scenesList" border>
                    <el-table-column label="资源类型" prop="zylx"></el-table-column>
                    <el-table-column label="大小" prop="dx"></el-table-column>
                    <el-table-column label="资源名称" prop="zymc"width="580px"></el-table-column>
                    <el-table-column label="创建时间" prop="cjsj"></el-table-column>
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
        name: "service-scenes",
        data(){
            return{
                scenesForm:{},
                value:null,
                scenesList:[],
                interfaceList:[],
                //查询参数
                queryParams:{
                    pageNum:1,
                    pageSize:10
                },
                total:3,
                interTotal:0,
            }
        },
        created() {
            this.getInterList();
            this.getList();
        },
        methods: {
            getList(){
              this.scenesList = [
                {
                  zylx:"schema压缩包",
                  dx:"2003",
                  zymc:"F01.zip",
                  cjsj:"2019-10-08 11:24:50",
                },
                {
                  zylx:"服务场景定义文档",
                  dx:"14725",
                  zymc:"服务场景定义文档_ChargeAcctF01_MbsdCore-1000-039001_A01账-账务处理_xlsx",
                  cjsj:"2019-10-08 11:24:50",
                }
              ]
            },
            getInterList(){

            }
        }
    }

</script>

<style scoped>
    .header{
        margin: 15px 0;
        border: #dcdfe6 1px solid;
        text-align: center;
        font-size: 20px;
        font-weight: normal;
    }
    .cell-hover{
      color: #17b9ba;
    }
    .cell-hover:hover{
      color: #2852a5;
    }
</style>
