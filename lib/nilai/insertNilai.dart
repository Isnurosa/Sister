// ignore_for_file: prefer_const_constructors, prefer_const_literals_to_create_immutables

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

class InsertNilai extends StatefulWidget {
  const InsertNilai({super.key});

  @override
  State<InsertNilai> createState() => _InsertNilaiState();
}

class _InsertNilaiState extends State<InsertNilai> {
  List<Map<String, dynamic>> namaMahasiswa = [];
  List<Map<String, dynamic>> namaMatkul = [];
  int? idMahasiswa;
  int? idMatkul;
  final nilai = TextEditingController();

  bool isNumeric(String str) {
    // ignore: unnecessary_null_comparison
    if (str == null || str.isEmpty) {
      return false;
    }
    final format = RegExp(r'^[0-9]+(\.[0-9]+)?$');
    return format.hasMatch(str);
  }

  Future<void> insertNilai() async {
    if (isNumeric(nilai.text)) {
      String urlInsert = "http://192.168.56.1:9003/api/v1/nilai";
      final Map<String, dynamic> data = {
        "idMahasiswa": idMahasiswa,
        "idMatkul": idMatkul,
        "nilai": int.parse(nilai.text)
      };

      try {
        var response = await http.post(Uri.parse(urlInsert),
            body: jsonEncode(data),
            headers: {'Content-Type': 'application/json'});

        if (response.statusCode == 200) {
          Navigator.pop(context);
        } else {
          print("Gagal");
        }
      } catch (e) {
        print(e);
      }
    } else {
      print("Bukan Angka Desimal");
    }
  }

  @override
  void initState() {
    super.initState();
    getMahasiswa();
    getMatkul();
  }

  Future<void> getMahasiswa() async {
    String urlMahasiswa = "http://192.168.56.1:9001/api/v1/mahasiswa";
    try {
      var response = await http.get(Uri.parse(urlMahasiswa));
      final List<dynamic> dataMhs = jsonDecode(response.body);
      setState(() {
        namaMahasiswa = List.from(dataMhs);
      });
    } catch (exc) {
      print(exc);
    }
  }

  Future<void> getMatkul() async {
    String urlMatkul = "http://192.168.56.1:9002/api/v1/matkul";
    try {
      var response = await http.get(Uri.parse(urlMatkul));
      final List<dynamic> dataMk = jsonDecode(response.body);
      setState(() {
        namaMatkul = List.from(dataMk);
      });
    } catch (exc) {
      print(exc);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Insert Data Nilai"),
        backgroundColor: Color.fromARGB(255, 224, 76, 150),
      ),
      body: Center(
        child: Container(
          margin: EdgeInsets.fromLTRB(0, 100, 0, 0),
          width: 800,
          padding: EdgeInsets.all(16),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.start,
            children: [
              DropdownButtonFormField(
                value: null,
                onChanged: (value) {
                  setState(() {
                    idMahasiswa = int.parse(value.toString());
                  });
                },
                items: namaMahasiswa.map((item) {
                  return DropdownMenuItem(
                      value: item["id"].toString(), child: Text(item["nama"]));
                }).toList(),
                decoration: InputDecoration(
                  labelText: "ID Mahasiswa",
                  hintText: "Pilih Mahasiswa",
                  prefixIcon: Icon(Icons.person_pin_outlined),
                  fillColor: Colors.pink.shade200,
                  border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(10),
                  ),
                ),
              ),
              SizedBox(height: 10),
              DropdownButtonFormField(
                value: null,
                onChanged: (value) {
                  setState(() {
                    idMatkul = int.parse(value.toString());
                  });
                },
                items: namaMatkul.map((item) {
                  return DropdownMenuItem(
                      value: item["id"].toString(),
                      child: Text(item["nama"].toString()));
                }).toList(),
                decoration: InputDecoration(
                  labelText: "ID Matakuliah",
                  hintText: "Pilih Matakuliah",
                  prefixIcon: Icon(Icons.bookmark_border),
                  fillColor: Colors.pink.shade200,
                  border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(10),
                  ),
                ),
              ),
              SizedBox(height: 10),
              TextField(
                controller: nilai,
                decoration: InputDecoration(
                  labelText: "Nilai",
                  hintText: "Ketikkan Jumlah Nilai",
                  prefixIcon: Icon(Icons.numbers_rounded),
                  fillColor: Colors.pink.shade200,
                  border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(10),
                  ),
                ),
              ),
              SizedBox(height: 20),
              SizedBox(
                height: 50,
                width: 200,
                child: TextButton(
                  style: TextButton.styleFrom(
                    backgroundColor: Colors.pink.shade400,
                  ),
                  onPressed: () {
                    insertNilai();
                  },
                  child: Text(
                    "SIMPAN",
                    style: TextStyle(
                      color: Colors.white,
                      fontSize: 20,
                    ),
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
