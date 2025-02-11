```toml
name = 'saveVacaction'
method = 'POST'
url = 'http://localhost:8080/vacations'
sortWeight = 3000000
id = '93fe4e0a-d44a-4bf1-9236-519bc19ec01f'

[body]
type = 'JSON'
raw = '''
{
  "from": "2025-04-22",
  "to": "2025-05-02",
  "takingEmployeeId": 2,
  "standInEmployeeId": 1,
  "status": "PENDING"
}'''
```
