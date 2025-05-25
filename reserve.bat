@echo off
curl -X POST http://localhost:8080/api/reservation/ ^
  -H "Content-Type: application/json" ^
  -H "X-User: notggalazzo" ^
  -d "{\"flightCode\":\"F001\",\"seatCodes\":[\"A2\",\"B1\"]}"
