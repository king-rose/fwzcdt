import request from '@/utils/request'

export function getTechDepts() {
	return request({
		url: '/system/techDepts',
		method: 'get'
	})
}

// 查询负责人列表
export function getPrincipalList() {
	return request({
		url: '/system/principals',
		method: 'get'
	})
}
// 查询所属层列表
export function getSystemLayers() {
	return request({
		url: '/system/systemLayers',
		method: 'get'
	})
}


// 查询系统列表
export function listSystem(query) {
    return request({
        url: '/system/list',
        method: 'get',
        params: query
    })
}

// 查询系统信息详细
export function getList(sysId) {
    return request({
        url: '/list/list/' + sysId,
        method: 'get'
    })
}

// 新增系统信息
export function addList(data) {
    return request({
        url: '/system',
        method: 'post',
        data: data
    })
}

// 修改系统信息
export function updateList(data) {
    return request({
        url: '/list/list',
        method: 'put',
        data: data
    })
}

// 删除系统信息
export function delList(sysId) {
    return request({
        url: '/list/list/' + sysId,
        method: 'delete'
    })
}

// 导出系统信息
export function exportList(query) {
    return request({
        url: '/list/list/export',
        method: 'get',
        params: query
    })
}
//系统数量
export function getSystemNum() {
    return request({
        url: '/system/sysNum',
        method: 'get'
    })
}

//场景数量
export function getSceneNum() {
    return request({
        url: '/system/sceneNum',
        method: 'get'
    })
}

//消费关系
export function getConsumeNum() {
    return request({
        url: '/system/consumeNum',
        method: 'get'
    })
}
//服务场景资产变化趋势
export function getChangeTrend(timeType) {
    return request({
        url:'/system/trend/'+timeType,
        method:'get'
    })
}

//场景消费占比
export  function sceneRatioChart() {
    return request({
        url:'/system/scene/ratio',
        method:'get'
    })
}
//服务场景占比
export  function serviceScene() {
	return request({
		url:'/system/scene/serviceScene',
		method:'get'
	})
}





