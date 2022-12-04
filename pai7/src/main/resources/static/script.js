
const BASE_URL = 'http://localhost:8080/students';

const showStudentCreateBox = async () => {
  await Swal.fire({
    title: 'Create student',
    html: `<input id="id" type="hidden">
    <input id="name" class="swal2-input" placeholder="Name">
    <input id="surname" class="swal2-input" placeholder="Surname">
    <input id="average" class="swal2-input" placeholder="Average">`,
    showCancelButton: true,
    focusConfirm: false,
    preConfirm: async () => {
      await addStudent({ name: document.getElementById('name').value, surname: document.getElementById('surname').value, average: document.getElementById('average').value });
    }
  })
}

const showStudentEditBox = async id => {
  const student = await getStudent(id);

  await Swal.fire({
    title: 'Update student',
    html: `<input id="id" type="hidden" value="${student.id}">
    <input id="name" class="swal2-input" placeholder="Name" value="${student.name}">
    <input id="surname" class="swal2-input" placeholder="Surname" value="${student.surname}">
    <input id="average" class="swal2-input" placeholder="Average" value="${student.average}">`,
    showCancelButton: true,
    focusConfirm: false,
    preConfirm: async () => {
      await updateStudent(id, { name: document.getElementById('name').value, surname: document.getElementById('surname').value, average: document.getElementById('average').value });
    }
  })
}

const getStudents = async () => {
  try {
    const response = await axios.get(BASE_URL);
    const students = response.data;

    let trHTML = '';
    for (const student of students) {
      trHTML += `<tr>
      <td> ${student.id} </td>
      <td> ${student.name} </td>
      <td> ${student.surname} </td>
      <td> ${student.average} </td>
      <td>
        <button type="button" onclick="showStudentEditBox(${student.id})">Edit</button>
        <button type="button" onclick="deleteStudent(${student.id})">Delete</button>
      </td>
      </tr>`;
    }
    document.getElementById("studentsTable").innerHTML = trHTML;

  } catch (errors) {
    console.error(errors);
  }
};

const getStudent = async id => {
  try {
    const response = await axios.get(`${BASE_URL}/${id}`);
    return response.data;
  } catch (errors) {
    console.error(errors);
  }
}

const addStudent = async student => {
  try {
    await axios.post(BASE_URL, student);
    getStudents();
  } catch (errors) {
    console.error(errors);
  }
}

const updateStudent = async (id, student) => {
  try {
    await axios.put(`${BASE_URL}/${id}`, student);
    getStudents();
  }
  catch {
    console.error(errors);
  }
}

const deleteStudent = async id => {
  await axios.delete(`${BASE_URL}/${id}`);
  getStudents();
}

getStudents();