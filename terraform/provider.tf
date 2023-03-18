terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 4.0"
    }
  }
}

# Configure the AWS Provider
provider "aws" {
  region              = "us-east-1"
  shared_config_files = ["~/.aws/credentials"]
  profile             = "default"
}