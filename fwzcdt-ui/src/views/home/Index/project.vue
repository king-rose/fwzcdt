<template>
    <el-row :gutter="10" class="project">
        <el-col :xs="12" :sm="12" :md="12" :lg="8" :xl="8">
            <div class="project-list">
                <div class="project-list-left">
                    <svg-icon class="project-list-left-icon" icon-class="systeminfo"></svg-icon>
                </div>
                <router-link class="project-list-right" :to="{ path:'/system/list'}">
                    系统:
                    <count-to :startVal='startVal' :endVal='sysVal' :duration='3000'></count-to>
                   <!-- @click="go('system')"-->
                </router-link>
            </div>
        </el-col>
        <el-col :xs="12" :sm="12" :md="12" :lg="8" :xl="8">
            <div class="project-list">
                <div class="project-list-left">
                    <!--<react class="project-list-left-icon" />-->
                    <svg-icon icon-class="react" class="project-list-left-icon"/>
                </div>
                <router-link class="project-list-right" :to="{ path:'/service/list'}">
                    服务场景:
                    <count-to :startVal='startVal' :endVal='sceneVal' :duration='3000'></count-to>
                    <!-- @click="go('system')"-->
                </router-link>
            </div>
        </el-col>
        <el-col :xs="12" :sm="12" :md="12" :lg="8" :xl="8">
            <div class="project-list">
                <div class="project-list-left">
                    <svg-icon icon-class="xf" class="project-list-left-icon"/>
                </div>
                <router-link class="project-list-right" :to="{ path:'/service/consume'}">
                    消费关系:
                    <count-to :startVal='startVal' :endVal='consumeVal' :duration='3000'></count-to>
                    <!-- @click="go('system')"-->
                </router-link>
            </div>
        </el-col>

    </el-row>
</template>

<script>
import countTo from 'vue-count-to';

import {getSystemNum,getSceneNum,getConsumeNum} from  "@/api/system/list"
export default {
    data(){
        return {
            startVal: 0,
            sysVal:0,
            sceneVal:0,
            consumeVal:0
        }
    },
    components:{
       countTo
    },
    created() {
        this.getData();
    },
    methods:{
        getData(){
            getSystemNum().then(res=>{
                this.sysVal = res.data
            })
            getSceneNum().then(res=>{
                this.sceneVal = res.data
            })
            getConsumeNum().then(res=>{
                this.consumeVal = res.data
            })
        },
        //跳转
        go(name){
            if (name==='system'){
                window.open('/system/list')
            }
            if (name==='service'){
                window.open('/dataManager/service')
            }
            if (name==='relation'){
                window.open('/manager/service')
            }

        }
    }
}
</script>

<style scoped>
.project{
    width: 100%;
    height: auto;
}
.project-list{
    width: 100%;
    height: 120px;
    /* background: #f7f7f7; */
    border-radius: 5px;
    cursor: pointer;
    padding: 10px 0 0 10px;
    box-sizing: border-box;
    background: url('../../../assets/image/vue.png');
    background-size: 100% 100%;
    display: flex;
    justify-content: space-around;
    margin-bottom: 15px;
}
.project-list-left{
    padding: 16px;
    box-sizing: border-box;
    width: 80px;
    border-radius: 5px;
}

.project-list-left:hover .project-list-left-icon{
    color: #ffffff;
}
.project-list-left-icon{
    width: 48px;
    height: 48px;
    fill: currentColor;
    color: #ffffff;

}
.project-list-right{
    flex: 1;
    color: #EA4E68;
    font-weight: bolder;
    font-size: 20px;
    line-height: 70px;
    text-align: center;
    color: #ffffff;
}
</style>
