variable "aws_region" {
  description = "AWS region to deploy resources"
  type        = string
  default     = "us-east-1"
}

variable "project_name" {
  description = "Project name tag"
  type        = string
  default     = "fiap-oficina"
}

variable "db_password" {
  description = "RDS Database administrator password"
  type        = string
  sensitive   = true
  default     = "postgres123Password!"
}
