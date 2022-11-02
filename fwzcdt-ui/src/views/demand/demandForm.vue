<template>
	<div class="app-container">
		<el-dialog :visible.sync="open" title="需求办理表单">
			<el-form :model="form" :inline="true">
				<el-form-item label="需求编号">
					<el-input readonly v-model="form.demandId"/>
				</el-form-item>
				<el-form-item label="需求名称">
					<el-input readonly v-model="form.demandName"/>
				</el-form-item>
				<el-form-item label="需求描述">
					<el-input readonly v-model="form.demandDes"/>
				</el-form-item>
				<el-form-item label="需求状态">
					<el-select readonly v-model="form.demandState">
						<el-option
						v-for="dict in demandStateOptions"
						:label="dict.dictLabel"
						:key="dict.dictValue"
						:value="dict.dictValue"
						>
						</el-option>
					</el-select>
				</el-form-item>

				<span>{{form}}</span>
			</el-form>
		</el-dialog>
	</div>
</template>

<script>
export default {
	name: "demandForm",
	data(){
		return{
			open:false,
			form:{},

			//需求状态
			demandStateOptions: [],
		}
	},
	created() {
		this.getDicts("aesb_demand_state").then(response => {
			this.demandStateOptions = response.data;
		});
	},
	methods:{

	}
}
</script>

<style lang="scss" scoped>
	.el-dialog__header{
		background-color: #09F8BA;
	}
	.el-dialog__title {
		color: white;
	}
</style>
