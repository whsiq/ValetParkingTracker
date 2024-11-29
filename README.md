# 🚗 Valet Parking Tracker

## 📝 Introduction

Valet Parking Tracker is an application used by valet managers and valet attendants to enable the tracking of vehicles parked in a valet garage. Valet attendants can check in a vehicle any time with information such as ticket number, parking spot, vehicle make, model, color, and notes. Valet guests can also remotely request their vehicle through connecting to the website and providing their ticket number.
The valet manager can generate reports showing detailed info of every vehicle in their garage at any given time.  
Users can interact with Valet Parking Tracker using RESTful service endpoints, through a UI, or both.

## Startup Notes

After cloning the repo you should be able to run it immediately. This application runs on port 8081.

## 🎨 Storyboard (screen mockups)
![ValetTrackerScreenMockups](https://github.com/user-attachments/assets/e35c5264-a649-43ff-a08b-16d9d8e4c7d2)
## ⚙️ Functional Requirements

1. As a valet attendant, I want to inventory the vehicles parked in my garage so I can keep track of what vehicles are there.

**Given**: Vehicles are being checked into a valet garage  
**When**: The valet selects "Check-in"  
**When**: The valet adds vehicle information to an entry  
**Then**: The vehicle and its information is saved to the inventory  

**Given**: The user has selected "Save Check-in"  
**When**: The user has not filled in a field  
**Then**: The application will prompt the user to fill in all fields  
**Then**: The application will not save the incomplete information to the inventory  

**Given**: The user has selected "Check-in"  
**When**: The user selects "Cancel"  
**Then**: The application does not save the entries  
**Then**: The application returns to the home screen 

**Given**: Vehicles are being pulled from a valet garage  
**When**: The valet selects "Pull vehicle"  
**Then**: The vehicle is removed from the inventory  

**Given**: Vehicles are parked in the valet garage  
**When**: The user searches for "Blue Toyota"  
**Then**: Valet Parking Tracker will list all Blue Toyotas  
 
2. As a valet manager, I want to generate reports so I can keep track of which vehicles are parked in the garage every night.  

**Given**: Vehicles are parked in the valet garage and inventoried  
**When**: The user selects "Generate Report"  
**Then**: Information from all vehicles are written into a .csv document  

**Given**: There are no vehicles parked in the valet garage  
**When**: The user selects "Generate Report"  
**Then**: A .csv document with headings is created but no vehicle information  

3. As a valet guest, I want to request my vehicle remotely so it pulled up by the time I'm at the valet stand. 

**Given**: The user has parked with valet  
**When**: The user visits a designated endpoint  
**When**: The user enters their ticket number  
**Then**: The application will notify the valet that a vehicle is requesting to be pulled  

**Given**: The user has visited the designated endpoint  
**When**: The user enters an invalid ticket number  
**Then**: The user is prompted that the ticket number was invalid  
**Then**: The application does not notify the valet  

**Given**: The valet has been notified that a vehicle is requesting to be pulled  
**When**: The valet selects "Pull vehicle"  
**Then**: The vehicle is removed from the inventory


## 📊 Class Diagram
![Class Diagram Valet Multiple Interfaces](assets/ValetParkingTracker.png)
## 🧾 JSON Schema

```
{
  "$schema": "http://json-schema.org/draft-07/schema#",
  "type": "object",
  "properties": {
    "ticket": {
      "type": "object",
      "properties": {
        "ticketId": {
          "type": "integer"
        },
        "customer": {
          "type": "object",
          "properties": {
            "customerId": {
              "type": "integer"
            },
            "firstName": {
              "type": "string"
            },
            "lastName": {
              "type": "string"
            }
          },
          "required": ["customerId", "firstName", "lastName"]
        },
        "vehicle": {
          "type": "object",
          "properties": {
            "vehicleId": {
              "type": "integer"
            },
            "make": {
              "type": "string"
            },
            "model": {
              "type": "string"
            },
            "color": {
              "type": "string"
            },
            "notes": {
              "type": "string"
            }
          },
          "required": ["vehicleId", "make", "model", "color"]
        },
        "parkingSpot": {
          "type": "string"
        }
      },
      "required": ["ticketId", "customer", "vehicle", "parkingSpot"]
    }
  },
  "required": ["ticket"]
}
```
*This is an example of a new ticket being posted*

## 🧑‍💼 Scrum Roles

**Scrum Master/Product Owner:** Chase Staggs  
**UI:** Drew Miluk, Dilpreet Supra  
**Business Logic and Persistence:**  Chase Staggs, Al Gassama, Yutong Chen  

## 🔗 GitHub Links

[Valet Parking Tracker](https://github.com/whsiq/ValetParkingTracker)  
[Milestones](https://github.com/whsiq/ValetParkingTracker/milestones)  
[Projects - Scrum boards here](https://github.com/whsiq/ValetParkingTracker/projects?query=is%3Aopen)  

## 📅 Group Standup

[Sunday 11 AM Meeting](https://teams.microsoft.com/l/meetup-join/19%3ameeting_MWM1ZGNjMjItNzMxMy00ODhjLTkzMTYtMmNmMWRlZmQ2M2Y4%40thread.v2/0?context=%7b%22Tid%22%3a%22f5222e6c-5fc6-48eb-8f03-73db18203b63%22%2c%22Oid%22%3a%221ebcc31f-9695-44be-8146-6f4a36582b82%22%7d)
