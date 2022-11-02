import request from '@/utils/request'

// 查询服务大类
export function smallClassList() {
	return request({
		url: '/oprService/smallClass',
		method: 'get'
	})
}

// 查询服务子类
export function headClassList() {
	return request({
		url: '/oprService/headClass',
		method: 'get'
	})
}


// 查询服务列表
export function serviceList(query) {
    return request({
        url: '/oprService/list',
        method: 'get',
        params: query
    })
}

// 下载服务列表导入模板
export function importTemplate() {
    return request({
        url: '/oprService/importTemplate',
        method: 'get'
    })
}

// 删除服务
export function delServiceListInfo(svcId) {
    return request({
        url: '/oprService/delete/'+svcId,
        method: 'delete'
    })
}
//获取详情
export function getServiceInfo(svcId) {
    return request({
        url: '/oprService/getServiceInfo/'+svcId,
        method: 'get'
    })
}
//编辑服务
export function updateService(data) {
    return request({
        url:'/oprService/updateService',
        method:'post',
        data:data
    })
}

export function addService(data) {
	return request({
		url:'/oprService/addService',
		method:'post',
		data:data
	})
}

