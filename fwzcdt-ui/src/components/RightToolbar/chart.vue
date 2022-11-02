<!-- @author Shiyn/   huangmx 20200807优化-->
<template>
	<div class="top-right-btn">
		<el-row>
			<el-tooltip :content="showSearch ? '隐藏搜索' : '显示搜索'" class="item" effect="dark" placement="top">
				<el-button circle icon="el-icon-search" size="mini" @click="toggleSearch()"/>
			</el-tooltip>
			<el-tooltip class="item" content="刷新" effect="dark" placement="top">
				<el-button circle icon="el-icon-refresh" size="mini" @click="refresh()"/>
			</el-tooltip>
			<el-tooltip class="item" content="视图" effect="dark" placement="top">
				<el-button circle icon="el-icon-pie-chart" size="mini" @click="toggleChart"/>
			</el-tooltip>
		</el-row>
	</div>
</template>
<script>
export default {
	name: "RightToolChartBar",
	data() {
		return {};
	},
	props: {
		showSearch: {
			type: Boolean,
			default: true,
		},
		showChart:{
			type: Boolean,
			default: false,
		}
	},

	methods: {
		//搜索
		toggleSearch() {
			this.$emit("update:showSearch", !this.showSearch);
		},
		toggleChart(){
			this.$emit("update:showChart", !this.showChart);
		},
		//刷新
		refresh() {
			this.$emit("queryTable");
		},
	},
};
</script>
