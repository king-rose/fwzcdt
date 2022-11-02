import request from '@/utils/request'
//删除需求信息
export function delDemandInfo(demandId) {
	return request({
		url: '/demand/'+demandId,
		method: 'delete'
	})
}

export function getProcess(query) {
	return request({
		url: '/demand/process',
		method: 'get',
		params:query
	})
}

export function DemandFaceDown(query) {
	return request({
		url: '/demand/downFace',
		method: 'put',
		data:query
	})
}

export function updateDemandFace(query) {
	return request({
		url: '/demand/updateFace',
		method: 'put',
		data:query
	})
}

// 任务单
export function listDemandTaskList(query) {
	return request({
		url: '/demand/taskList',
		method: 'get',
		params: query
	})
}

// 关联系统接口
export function demandAsso(query) {
	return request({
		url: '/demand/asso',
		method: 'get',
		params: query
	})
}


// 获取需求列表
export function listDemand(query) {
    return request({
        url: '/demand/list',
        method: 'get',
        params: query
    })
}
// 获取需求审批列表
export function listDemandAcceptList(query) {
	return request({
		url: '/demand/acceptList',
		method: 'get',
		params: query
	})
}


//添加需求
export function demandAdd(data){
    return request({
        url: '/demand',
        method: 'post',
        data: data
    })
}

//获取需求详情
export function getDemandInfo(demandId){
    return request({
        url: '/demand/'+demandId,
        method: 'get'
    })
}

//需求变更
export function updateDemand(query){
    return request({
        url: '/demand',
        method: 'put',
        data:query
    })
}
