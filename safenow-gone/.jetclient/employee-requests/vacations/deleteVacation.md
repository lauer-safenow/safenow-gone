```toml
name = 'deleteVacation'
method = 'DELETE'
url = 'http://localhost:8080/vacations/{id}'
sortWeight = 5000000
id = 'ed260dde-937d-4a21-af1d-e35d68e80f71'

[[pathVariables]]
key = 'id'
value = '9a705023-7b27-4fa1-b0d3-ade31220f79d'

[body]
type = 'JSON'
raw = '''
{
  "from": "2025-04-22",
  "to": "2025-04-22",
  "takingEmployeeId": 1,
  "status": "PENDING"
}'''
```
