import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ReactiveFormsModule,FormArray,FormGroup,FormControl,FormControlName,FormsModule } from '@angular/forms';
import { Student } from './student.model';
import { Validators } from '@angular/forms';
import { StudentService,PageResponse } from '../../services/student.service';

@Component({
  selector: 'app-student',
  imports: [CommonModule,ReactiveFormsModule,FormsModule],
  templateUrl: './student.component.html',
  styleUrl: './student.component.css'
})
export class StudentComponent implements OnInit{

studentForm!: FormGroup;
  activeStudentForm!: FormGroup;
  isEditing = false;
  editingStudentId: number | null = null;

  // Search & Pagination Controls
  searchTerm: string = '';
  currentPage: number = 0;
  pageSize: number = 5;
  totalPages: number = 0;
  totalElements: number = 0;

  constructor(private studentService: StudentService) {}

  ngOnInit(): void {
this.initForms();
    this.loadStudents();  
  }
  initForms(): void {
    this.studentForm = new FormGroup({
      studentsArray: new FormArray([])
    });
    this.resetActiveForm();
  }

  get studentsArray(): FormArray {
    return this.studentForm.get('studentsArray') as FormArray;
  }

  get activeControls() {
    return this.activeStudentForm.controls;
  }

  createStudentGroup(student?: Student): FormGroup {
    return new FormGroup({
      id: new FormControl(student?.id ?? null),
      name: new FormControl(student?.name ?? '', [Validators.required, Validators.minLength(3)]),
      email: new FormControl(student?.email ?? '', [Validators.required, Validators.email]),
      course: new FormControl(student?.course ?? '', [Validators.required])
    });
  }

  // Load students from Spring Boot backend
  loadStudents(): void {
    this.studentService.getStudents(this.searchTerm, this.currentPage, this.pageSize)
      .subscribe({
        next: (response: PageResponse<Student>) => {
          this.studentsArray.clear();
          response.content.forEach(student => {
            this.studentsArray.push(this.createStudentGroup(student));
          });
          this.totalPages = response.totalPages;
          this.totalElements = response.totalElements;
        },
        error: (err) => console.error('Failed to fetch students:', err)
      });
  }

  // Handle Search Input
  onSearch(): void {
    this.currentPage = 0; // Reset to first page on new search query
    this.loadStudents();
  }

  // Pagination Handlers
  goToPage(page: number): void {
    if (page >= 0 && page < this.totalPages) {
      this.currentPage = page;
      this.loadStudents();
    }
  }

  onPageSizeChange(): void {
    this.currentPage = 0;
    this.loadStudents();
  }

  onSubmit(): void {
    if (this.activeStudentForm.invalid) {
      this.activeStudentForm.markAllAsTouched();
      return;
    }

    const payload: Student = this.activeStudentForm.value;

    if (this.isEditing && this.editingStudentId !== null) {
      this.studentService.updateStudent(this.editingStudentId, payload).subscribe({
        next: () => {
          this.loadStudents();
          this.resetActiveForm();
        }
      });
    } else {
      this.studentService.createStudent(payload).subscribe({
        next: () => {
          this.loadStudents();
          this.resetActiveForm();
        }
      });
    }
  }

  onEdit(index: number): void {
    const selectedGroup = this.studentsArray.at(index) as FormGroup;
    this.isEditing = true;
    this.editingStudentId = selectedGroup.get('id')?.value;
    this.activeStudentForm.patchValue(selectedGroup.value);
  }

  onDelete(id: number): void {
    if (confirm('Are you sure you want to delete this record?')) {
      this.studentService.deleteStudent(id).subscribe({
        next: () => this.loadStudents()
      });
    }
  }

  resetActiveForm(): void {
    this.activeStudentForm = this.createStudentGroup();
    this.isEditing = false;
    this.editingStudentId = null;
  }

}
