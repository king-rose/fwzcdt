import request from '@/utils/request'

export function getServiceSceneInfo(svId) {
	return request({
		url: '/oprScene/getServiceSceneInfo/'+svId,
		method: 'get'
	})
}


// 查询流程定义列表
export function sceneList(query) {
    return request({
        url: '/oprScene/list',
        method: 'get',
        params: query
    })
}

// 下载服务列表导入模板
export function importTemplate() {
    return request({
        url: '/oprScene/importTemplate',
        method: 'get'
    })
}

//删除/批量删除
export function delSceneVersion(svIds) {
    return request({
        url:'/oprScene/remove/'+svIds,
        method:'delete'
    })
}

//基本信息
export function sceneBasicInfo(svId) {
    return request({
        url:'oprScene/basic/'+svId,
        method:'get'
    })
}

//场景资源
export function getSceneResourceInfo(svId) {
    return request({
        url:'oprScene/resource/'+svId,
        method:'get'
    })
}

//场景指标
export function getSceneQuotaInfo(svId) {
    return request({
        url:'oprScene/quato/'+svId,
        method:'get'
    })
}

//场景字段信息
export function getSceneFiledIn(query) {
	return request({
		url:'oprScene/getSceneFiledIn',
		method:'get',
		params:query
	})
}
//场景字段信息
export function getSceneFiledOut(query) {
	return request({
		url:'oprScene/getSceneFiledOut',
		method:'get',
		params:query
	})
}



//被消费列表
export function getConsumeInfo(svId) {
    return request({
        url:'oprScene/consume/'+svId,
        method:'get'
    })
}

//变更历史
export function getChangeInfo(svId) {
    return request({
        url:'oprScene/change/'+svId,
        method:'get'
    })
}
//场景编辑
export function sceneEdit(query) {
    return request({
        url:'oprScene/edit',
        method:'post',
        params: query
    })
}
//场景字段编辑
/*export function fieldEdit(query) {
	return request({
		url: '/oprScene/sceneFieldEdit',
		method: 'post',
		params:query
	})
}*/
//场景指标编辑
export function quatoEdit(query) {
	return request({
		url: '/oprScene/sceneQuatodEdit',
		method: 'post',
		params:query
	})
}

//场景定义
export function getScenarioDefinedIn(query) {
    return request({
        url:'oprScene/defined/in',
        method:'get',
        params:query
    })
}
//场景定义
export function getScenarioDefinedOut(query) {
    return request({
        url:'oprScene/defined/out',
        method:'get',
        params:query
    })
}

//场景发布
export function sceneRelease(data) {
    return request({
        url:'oprScene/release',
        method:'post',
        data:data
    })
}
