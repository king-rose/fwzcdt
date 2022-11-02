import request from '@/utils/request'

export function delMappingLIst(ids) {
	return request({
		url:'/mapping/'+ids,
		method:'delete'
	})
}


//提供方系统
export function proSysList() {
	return request({
		url:'/mapping/proList',
		method:'get'
	})
}

//请求方系统
export function reqSysList() {
	return request({
		url:'/mapping/reqList',
		method:'get'
	})
}


//服务场景消费
export  function consumeRatioData(proSysId) {
    return request({
        url:'/mapping/relation/'+proSysId,
        method:'get'
    })
}

//服务场景消费列表
export  function listMapping(query) {
    return request({
        url:'/mapping/list',
        method:'get',
        params:query
    })
}
