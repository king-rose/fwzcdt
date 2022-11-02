import request from '@/utils/request'

// 查询标准数据元字典键列表
export function listDataSource(query) {
  return request({
    url: '/management/dataSource/list',
    method: 'get',
    params: query
  })
}

// 查询标准数据元字典键详细
export function getDataSource(stdDictId) {
  return request({
    url: '/management/dataSource/' + stdDictId,
    method: 'get'
  })
}

// 新增标准数据元字典键
export function addDataSource(data) {
  return request({
    url: '/management/dataSource',
    method: 'post',
    data: data
  })
}
// 批量新增标准数据元字典键
export function foreachAddDataSource(data) {
  return request({
    url: '/management/dataSource/foreachAdd',
    method: 'post',
    data: data
  })
}
// 修改标准数据元字典键
export function updateDataSource(data) {
  return request({
    url: '/management/dataSource',
    method: 'put',
    data: data
  })
}

// 删除标准数据元字典键
export function delDataSource(stdDictId) {
  return request({
    url: '/management/dataSource/' + stdDictId,
    method: 'delete'
  })
}

// 导出标准数据元字典键
export function exportDataSource(query) {
  return request({
    url: '/management/dataSource/export',
    method: 'get',
    params: query
  })
}
