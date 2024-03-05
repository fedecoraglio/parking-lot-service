In order to start the application , it is necessary to run the ParkingLotServiceApplication

The list of APIs are:

- Vehicles Types
url: /v1/vehicles-types
Method: GET

- Slot Types
url: /v1/slots-types
Method: GET

- Slots Remaining
url: /v1/slots
Method: GET

- Slots Remaining by type
url: /v1/slots/type/{slotTypeId}/remaining
Method: GET

- Parking Slot Vehicle
url: /v1/parking-slots-vehicles
Method: POST
Body: { vehicleVim: string, vehicleTypeId: string}

- Release Slot Vehicle
url: /v1/releasing-slots-vehicles
Method: POST
Body: { vehicleVim: string }
  
