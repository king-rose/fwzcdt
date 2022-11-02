<template>
  <div class="app-container">
    <el-container>
      <el-header>被消费清单</el-header>
      <el-main>
        <el-form :model="queryParams" ref="queryForm"  :inline="true">
          <el-form-item label="服务消费方" prop="fwxff">
            <el-select
              v-model="queryParams.status"
              placeholder="服务消费方"
              clearable
              size="small"
              style="width: 240px"
            >
              <el-option
                v-for="dict in fwxffList"
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
          <el-table-column label="消费方编号" align="center" width="150" prop="xffbh" />
          <el-table-column label="消费方名称" align="center"  prop="xffmc" />
          <el-table-column label="服务编号" align="center"  prop="fwbh" />
          <el-table-column label="服务名称" align="center"  prop="fwmc" />
          <el-table-column label="场景码" align="center"  prop="cjm" />
          <el-table-column label="场景名称" align="center"  prop="cjmc" />
          <el-table-column label="状态" align="center"  prop="status" />
        </el-table>
      </el-main>
    </el-container>
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
        fwxffList:[
          {value:0,label:'LZCBYC-LZ核心系统压测'},
          {value:1,label:'LZCNPS2-LZ二代支付'},
          {value:3,label:'LZCORBS-LZ核心系统'},
          {value:4,label:'LZMSGSY-LZ短信平台'},
          {value:5,label:'SDCORE-核心系统'},
          {value:6,label:'WMS-理财销售系统'},
          {value:7,label:'ZhT01-马国成测试01'},
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
