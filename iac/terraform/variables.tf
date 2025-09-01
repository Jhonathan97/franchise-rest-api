# variables.tf
variable "region" {
  type    = string
  default = "us-east-1"
}

variable "db_name" {
  type    = string
  default = "franchises"
}

variable "db_username" {
  type    = string
  default = "franchise"
}

variable "db_password" {
  type    = string
  default = "franchise"
}
