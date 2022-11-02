import request from '@/utils/request'

// 查询请假测试列表
export function listSimple(query) {
  return request({
    url: '/simple/simple/list',
    method: 'get',
    params: query
  })
}

// 查询请假测试详细
export function getSimple(id) {
  return request({
    url: '/simple/simple/' + id,
    method: 'get'
  })
}

// 新增请假测试
export function addSimple(data) {
  return request({
    url: '/simple/simple',
    method: 'post',
    data: data
  })
}

// 修改请假测试
export function updateSimple(data) {
  return request({
    url: '/simple/simple',
    method: 'put',
    data: data
  })
}

// 删除请假测试
export function delSimple(id) {
  return request({
    url: '/simple/simple/' + id,
    method: 'delete'
  })
}

// 导出请假测试
export function exportSimple(query) {
  return request({
    url: '/simple/simple/export',
    method: 'get',
    params: query
  })
}