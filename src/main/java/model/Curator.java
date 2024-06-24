package model;

import service.IdGenerator;

import java.io.InputStream;
import java.util.Arrays;

public class Curator {
        private int id;
        private String name;
        private String surname;
        private Subject subjects;
        private ExperienceLevel experience;
        private String email;
        private InputStream photo;

        public Curator(String name, String surname, Subject subjects, ExperienceLevel experience,
                       String email, InputStream photo) {
                this.name = name;
                this.surname = surname;
                this.subjects = subjects;
                this.experience = experience;
                this.email = email;
                this.photo = photo;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getSurname() {
                return surname;
        }

        public void setSurname(String surname) {
                this.surname = surname;
        }

        public Subject getSubjects() {
                return subjects;
        }

        public void setSubjects(Subject subjects) {
                this.subjects = subjects;
        }

        public ExperienceLevel getExperience() {
                return experience;
        }

        public void setExperience(ExperienceLevel experience) {
                this.experience = experience;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public InputStream getPhoto() {
                return photo;
        }

        public void setPhoto(InputStream photo) {
                this.photo = photo;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                Curator curator = (Curator) o;

                if (getName() != null ? !getName().equals(curator.getName()) : curator.getName() != null) return false;
                if (getSurname() != null ? !getSurname().equals(curator.getSurname()) : curator.getSurname() != null)
                        return false;
                if (getSubjects() != curator.getSubjects()) return false;
                if (getExperience() != curator.getExperience()) return false;
                return getEmail() != null ? getEmail().equals(curator.getEmail()) : curator.getEmail() == null;
        }

        @Override
        public int hashCode() {
                int result = getName() != null ? getName().hashCode() : 0;
                result = 31 * result + (getSurname() != null ? getSurname().hashCode() : 0);
                result = 31 * result + (getSubjects() != null ? getSubjects().hashCode() : 0);
                result = 31 * result + (getExperience() != null ? getExperience().hashCode() : 0);
                result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
                return result;
        }
}
