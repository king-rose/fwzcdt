<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="6"><div class="grid-content-left bg-left">关联接口:</div></el-col>
            <el-col :span="6"><div class="grid-content-right">{{form.svInterface}}</div></el-col>
            <el-col :span="6"><div class="grid-content-left bg-left">关联接口所属系统:</div></el-col>
            <el-col :span="6"><div class="grid-content-right bg-light">{{form.sysName}}</div></el-col>
        </el-row>

        <el-row :gutter="20">
            <el-col :span="6"><div class="grid-content-left bg-left">项目编号:</div></el-col>
            <el-col :span="6"><div class="grid-content-right bg-light">{{form.projectCode}}</div></el-col>
            <el-col :span="6"><div class="grid-content-left bg-left">申请单:</div></el-col>
            <el-col :span="6"><span :title="form.applicationId" class="grid-content-key">{{form.applicationId}}</span></el-col>
        </el-row>

        <el-row :gutter="20">
            <el-col :span="6"><div class="grid-content-left bg-left">变更原因:</div></el-col>
            <el-col :span="6"><div class="grid-content-right">{{form.updateReason}}</div></el-col>
            <el-col :span="6"><div class="grid-content-left bg-left">变更内容:</div></el-col>
            <el-col :span="6"><div class="grid-content-right">{{form.updateContent}}</div></el-col>
        </el-row>

        <el-row :gutter="20">
            <el-col :span="6"><div class="grid-content-left bg-left">UAT计划投产时间:</div></el-col>
            <el-col :span="6"><div class="grid-content-right">2020-03-24</div></el-col>
            <el-col :span="6"><div class="grid-content-left bg-left">集成方式:</div></el-col>
            <el-col :span="6"><div class="grid-content-right">{{form.svIntegrationMode==0 ? '内联ESB': form.svIntegrationMode==1 ? '外联ESB':'直连'}}</div></el-col>
        </el-row>

        <el-row :gutter="20">
            <el-col :span="6"><div class="grid-content-left bg-left">是否是配:</div></el-col>
            <el-col :span="6"><div class="grid-content-right">{{form.jkAdapFlag==0?'不适配':'适配'}}</div></el-col>
            <el-col :span="6"><div class="grid-content-left bg-left">是否对标:</div></el-col>
            <el-col :span="6"><div class="grid-content-right">{{form.stdFieldCheck==0 ? '不对标':'对标'}}</div></el-col>
        </el-row>

        <el-row :gutter="20">
            <el-col :span="6"><div class="grid-content-left bg-left">是否校验:</div></el-col>
            <el-col :span="6"><div class="grid-content-right">{{form.xmlValidFlag==0 ? '不校验':'校验'}}</div></el-col>
            <el-col :span="6"><div class="grid-content-left bg-left">最后更新时间:</div></el-col>
            <el-col :span="6"><div class="grid-content-right">{{form.svUpdateTime}}</div></el-col>
        </el-row>

        <el-row :gutter="20">
            <el-col :span="6"><div class="grid-content-left bg-left">服务场景描述:</div></el-col>
            <el-col :span="6"><div class="grid-content-right">{{form.scnDes}}</div></el-col>
            <el-col :span="6"><div class="grid-content-left bg-left">SIT部署记录:</div></el-col>
            <el-col :span="6"><div class="grid-content-right"></div></el-col>
        </el-row>

        <el-row :gutter="20">
            <el-col :span="6"><div class="grid-content-left bg-left">报文格式:</div></el-col>
            <el-col :span="6"><div class="grid-content-right">json</div></el-col>
            <el-col :span="6"><div class="grid-content-left bg-left">通讯协议:</div></el-col>
            <el-col :span="6"><div class="grid-content-right">HTTP</div></el-col>
        </el-row>

        <el-row :gutter="20">
            <el-col :span="6"><div class="grid-content-left bg-left">HTTP URL:</div></el-col>
            <el-col :span="6"><div class="grid-content-right">{{form.svHttpUrl}}</div></el-col>
            <el-col :span="6"><div class="grid-content-left bg-left">场景类型:</div></el-col>
            <el-col :span="6"><div class="grid-content-right">{{form.svType==1?'服务场景':form.svType==2?'主题场景':'组合服务场景'}}</div></el-col>
        </el-row>

        <el-table  :data="list">
            <el-table-column label="资源类型" min-width="20%" prop="resourceType"  >
                <template slot-scope="scope">
                    <span>{{scope.row.resourceType==1?'schema压缩包':'场景Excel'}}</span>
                </template>
            </el-table-column>
            <el-table-column label="大小" min-width="10%" prop="resourceSize"  />
            <el-table-column label="资源名称" min-width="40%" prop="resourceName"  >
                <template slot-scope="scope">
                    <span class="bg-light">{{scope.row.resourceName}}</span>
                </template>
            </el-table-column>
            <el-table-column label="创建时间" min-width="30%" prop="crtTime"  />
        </el-table>
        <pagination
            v-show="total>0"
            :total="total"
            :page.sync="queryParams.pageNum"
            :limit.sync="queryParams.pageSize"
            @pagination="geResourceList"
        />
    </div>
</template>

<script>
    import {sceneBasicInfo,getSceneResourceInfo} from "@/api/service/oprscenario";

    export default {
        name: "jbxx",
        props:{
            svId: String,
            required: true
        },
        data(){
            return{
                form:{},
                //表格数据
                list:[],
                total:0,
                //查询参数
                queryParams:{
                    pageNum:1,
                    pageSize:10
                }
            };
        },
        created() {
            this.getSceneBasicInfo(this.svId);
            this.geResourceList(this.svId);
        },
        methods :{
            geResourceList(svId){
                getSceneResourceInfo(svId).then(res=>{
                    this.list = res.data;
                    this.total = res.data.length
                })

            },
            //基本信息
            getSceneBasicInfo(svId){
                sceneBasicInfo(svId).then(res=>{
                    this.form = res.data
                })
            }

        }
    }
</script>


<style scoped lang="scss">
    .el-row {
        margin-bottom: 20px;
    &:last-child {
         margin-bottom: 0;
     }
    }
    .el-col {
        border-radius: 4px;
    }

    .bg-light{
        color: #4ab7bd;
    }
    .bg-light:hover{
        cursor: pointer;
        text-decoration: underline;
    }

    .bg-left{
        color: #1e1e1e;
    }
    .bg-right{
        color: #5a5e66;
    }

    .grid-content-left {
        font-size: 15px;
        text-align: right;
        min-height: 36px;
        line-height: 36px;
    }
    .grid-content-right {
        font-size: 15px;
        text-align: center;
        min-height: 36px;
        line-height: 36px;
    }

    .grid-content-key{
        font-size: 14px;
        text-align: center;
        min-height: 36px;
        line-height: 36px;
        display: -webkit-box;
        -webkit-line-clamp: 2;
        -webkit-box-orient: vertical;
        overflow: hidden;
    }

</style>
