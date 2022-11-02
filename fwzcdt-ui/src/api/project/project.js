import request from '@/utils/request'

// 查询项目信息列表
export function listProject(query) {
  return request({
    url: '/project/list',
    method: 'get',
    params: query
  })
}

// 查询项目信息详细
export function getProject(projectCode) {
  return request({
    url: '/project/' + projectCode,
    method: 'get'
  })
}

// 新增项目信息
export function addProject(data) {
  return request({
    url: '/project',
    method: 'post',
    data: data
  })
}

// 修改项目信息
export function updateProject(data) {
  return request({
    url: '/project',
    method: 'put',
    data: data
  })
}

// 删除项目信息
export function delProject(projectCode) {
  return request({
    url: '/project/' + projectCode,
    method: 'delete'
  })
}

// 导出项目信息
export function exportProject(query) {
  return request({
    url: '/project/export',
    method: 'get',
    params: query
  })
}
