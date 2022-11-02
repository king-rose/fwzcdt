<template>
    <div class="app-container">
        <el-tabs type="border-card">
            <el-tab-pane>
                <span slot="label"><i class="el-icon-info"></i> 接口属性</span>
                <Attr></Attr>
            </el-tab-pane>
            <el-tab-pane>
                <span slot="label"><i class="el-icon-thumb"></i> 接口定义</span>
                <Definition></Definition>
            </el-tab-pane>
            <el-tab-pane>
                <span slot="label"><i class="el-icon-shopping-cart-full"></i> 被消费列表</span>
                <Consumption></Consumption>
            </el-tab-pane>
            <el-tab-pane>
                <span slot="label"><i class="el-icon-price-tag"></i> 场景列表</span>
                <sceneList></sceneList>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>

<script>
    import Attr from '@/views/system/interface/components/attr'
    import Definition from '@/views/system/interface/components/definition'
    import Consumption from '@/views/system/interface/components/consumption'
    import sceneList from '@/views/system/interface/components/sceneList'
    export default {
        name: "interfaceInfo",
        components:{Attr,Definition,Consumption,sceneList,}
    }
</script>

<style scoped>

</style>
