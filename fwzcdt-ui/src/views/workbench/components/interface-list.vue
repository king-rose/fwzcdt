<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm"  :inline="true">
      <el-form-item label="模糊查询" prop="gjzcx">
        <el-input
          v-model="queryParams.gjzcx"
          placeholder="请输入关键字查询"
          clearable
          size="small"
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select
          v-model="queryParams.status"
          placeholder="状态"
          clearable
          size="small"
          style="width: 240px"
        >
          <el-option
            v-for="dict in zhuangTaiList"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <el-table v-loading="loading" :data="interfaceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="50" align="center" />
      <el-table-column label="接口码" align="center" width="150" prop="jkm" />
      <el-table-column label="接口名称" align="center"  prop="jkmc" />
      <el-table-column label="所属系统" align="center"  prop="ssxt" />
      <el-table-column label="接口版本名" align="center"  prop="jkbbm" />
      <el-table-column label="报文类型" align="center"  prop="bwlx" />
      <el-table-column label="投产状态" align="center"  prop="tczt" />
      <el-table-column label="状态" align="center"  prop="status" />
    </el-table>
  </div>
</template>

<script>
  export default {
    name: "application-function",
    data(){
      return{
        interfaceList:[],
        // 遮罩层
        loading: true,
        open:false,
        title:'',
        // 表单参数
        form: {},
        rules:{},
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          gjzcx: undefined,
        },
        zhuangTaiList:[
          {value:0,label:'可用'},
          {value:1,label:'不可用'}
        ],
      }
    },
    created() {
      this.getInterfaceList()
    },
    methods:{
      /** 重置按钮操作 */
      resetQuery() {
        this.handleQuery();
      },
      /** 新增应用功能 */
      handleAdd(){
        this.open = true;
        this.title = "新增应用功能";
      },
      getInterfaceList(){
        this.loading=false
      },
      // 取消按钮
      cancel() {
        this.open = false;
      },
      submitForm(){
        this.open = false
      },
      // 多选框选中数据
      handleSelectionChange(selection) {

      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
      },
    },
  }
</script>

<style scoped>
  .el-header{
    background-color: #f7f7f7;
    border: solid 1px #e2e2e2;
    font-size: 20px;
    border-bottom:none;
    line-height: 60px;
  }
  .el-main {
    font-size: 15px;
    border: solid 1px #e2e2e2;
  }
</style>
