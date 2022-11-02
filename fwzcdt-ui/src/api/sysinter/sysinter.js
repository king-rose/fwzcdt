import request from '@/utils/request'

// 查询系统接口信息列表
export function listSysinter(query) {
  return request({
    url: '/sysinter/sysinter/list',
    method: 'get',
    params: query
  })
}

// 根据systemId查询接口列表
export function getListBySysIds(params) {
    return request({
        url: '/sysinter/sysinter/getListBySysIds',
        method: 'get',
        params:params
    })
}

// 查询系统接口信息详细
export function getSysinter(jkVersionId) {
  return request({
    url: '/sysinter/sysinter/' + jkVersionId,
    method: 'get'
  })
}

// 新增系统接口信息
export function addSysinter(data) {
  return request({
    url: '/sysinter/sysinter',
    method: 'post',
    data: data
  })
}

// 修改系统接口信息
export function updateSysinter(data) {
  return request({
    url: '/sysinter/sysinter',
    method: 'put',
    data: data
  })
}

// 删除系统接口信息
export function delSysinter(jkVersionId) {
  return request({
    url: '/sysinter/sysinter/' + jkVersionId,
    method: 'delete'
  })
}

// 导出系统接口信息
export function exportSysinter(query) {
  return request({
    url: '/sysinter/sysinter/export',
    method: 'get',
    params: query
  })
}
